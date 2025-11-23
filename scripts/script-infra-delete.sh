#!/usr/bin/env bash
set -euo pipefail
# Usage:
#   source scripts/script-infra-variables.env
#   bash scripts/script-infra-delete.sh

echo "=== Removendo recursos criados (resource group $RG_NAME) ==="
az account set --subscription "$AZURE_SUBSCRIPTION_ID"
az group delete -n $RG_NAME --yes --no-wait
echo "Pedido de remoção enviado. Aguarde até os recursos serem excluídos no portal ou via az group show."
