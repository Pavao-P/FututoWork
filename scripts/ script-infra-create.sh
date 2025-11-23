#!/usr/bin/env bash
set -euo pipefail
# Usage:
#   source scripts/script-infra-variables.env
#   bash scripts/script-infra-create.sh

echo "=== Criando infraestrutura no Azure (Container Apps + ACR + MySQL Flexible Server) ==="

if [ -z "${AZURE_SUBSCRIPTION_ID:-}" ]; then
  echo "ERRO: defina AZURE_SUBSCRIPTION_ID em script-infra-variables.env"
  exit 1
fi

az account set --subscription "$AZURE_SUBSCRIPTION_ID"

echo "Criando resource group: $RG_NAME"
az group create -n $RG_NAME -l $AZURE_LOCATION

echo "Criando Azure Container Registry: $ACR_NAME"
az acr create -n $ACR_NAME -g $RG_NAME --sku $ACR_SKU --admin-enabled true

ACR_LOGIN_SERVER=$(az acr show -n $ACR_NAME -g $RG_NAME --query loginServer -o tsv)
echo "ACR Login Server: $ACR_LOGIN_SERVER"

echo "Criando servidor MySQL Flexible Server (PaaS): $MYSQL_SERVER_NAME"
az mysql flexible-server create -g $RG_NAME -n $MYSQL_SERVER_NAME -l $AZURE_LOCATION \
  --sku-name Standard_B1ms --admin-user $MYSQL_ADMIN_USER --admin-password "$MYSQL_ADMIN_PASSWORD" \
  --version 8.0 --storage-size 32

echo "Configurando firewall do MySQL para permitir conexões do Container Apps (0.0.0.0 temporarily) - ajuste em produção"
az mysql flexible-server firewall-rule create -g $RG_NAME -s $MYSQL_SERVER_NAME -n AllowAll \
  --start-ip-address 0.0.0.0 --end-ip-address 0.0.0.0 || true

echo "Criando Banco de Dados: $MYSQL_DB_NAME"
az mysql flexible-server db create -g $RG_NAME -s $MYSQL_SERVER_NAME -n $MYSQL_DB_NAME || true

echo "Criando Container Apps Environment: $CONTAINERAPPS_ENV_NAME"
az containerapp env create -g $RG_NAME -n $CONTAINERAPPS_ENV_NAME --location $AZURE_LOCATION

echo "Infra criada com sucesso."
echo "== Próximo passo: construir e enviar imagens para ACR (az acr build) e criar Container Apps usando az containerapp create =="
echo "ACR_LOGIN_SERVER=$ACR_LOGIN_SERVER"