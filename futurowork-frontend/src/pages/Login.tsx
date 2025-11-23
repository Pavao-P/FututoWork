import React, { useState } from 'react';
import axios from 'axios';

export default function Login(){
  const [user,setUser]=useState('');
  const [pass,setPass]=useState('');
  async function submit(e:any){
    e.preventDefault();
    const res = await axios.post(import.meta.env.VITE_API_URL + '/auth/login', { username: user, password: pass });
    localStorage.setItem('token', res.data.token);
    window.location.href = '/';
  }
  return (
    <form onSubmit={submit}>
      <h2>Entrar - FuturoWork</h2>
      <input placeholder="Usuário" value={user} onChange={e=>setUser(e.target.value)} /><br/>
      <input type="password" placeholder="Senha" value={pass} onChange={e=>setPass(e.target.value)} /><br/>
      <button>Entrar</button>
    </form>
  );
}
