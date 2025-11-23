import React, { useEffect, useState } from 'react';
import axios from 'axios';

export default function Dashboard(){
  const [courses, setCourses] = useState<any[]>([]);
  useEffect(()=>{
    const token = localStorage.getItem('token');
    axios.get(import.meta.env.VITE_API_URL + '/courses', { headers: { Authorization: 'Bearer ' + token } })
      .then(r => setCourses(r.data.content))
      .catch(() => {});
  }, []);
  return (
    <div>
      <h1>FuturoWork - Dashboard</h1>
      <a href="/login">Login</a>
      <ul>{courses.map(c=> <li key={c.id}>{c.title} ({c.durationHours}h)</li>)}</ul>
    </div>
  );
}
