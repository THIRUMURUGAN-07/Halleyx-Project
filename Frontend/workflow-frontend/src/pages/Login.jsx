import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const Login = () => {

  const [email,setEmail] = useState("");
  const [password,setPassword] = useState("");

  const navigate = useNavigate();

  const login = async () => {

    const res = await axios.post(
      "http://localhost:8080/auth/login",
      {email,password}
    );

    localStorage.setItem("token",res.data.token);
   

    navigate("/dashboard");
  };
 

  return (

    <div className="flex items-center justify-center h-screen">

      <div className="bg-white shadow p-6 rounded w-80">

        <h2 className="text-xl font-bold mb-4">Login</h2>

        <input
          className="border p-2 w-full mb-3"
          placeholder="Email"
          onChange={(e)=>setEmail(e.target.value)}
        />

        <input
          type="password"
          className="border p-2 w-full mb-3"
          placeholder="Password"
          onChange={(e)=>setPassword(e.target.value)}
        />

        <button
          onClick={login}
          className="bg-blue-500 text-white w-full p-2 rounded"
        >
          Login
        </button>
        {/* <button 
        onClick={regsiter}
        className="bg-red-500 text-white w-full p-2 rounded"> register?</button> */}

      </div>

    </div>
  );
};

export default Login;