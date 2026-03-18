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
    localStorage.setItem("name",res.data.name);
    localStorage.setItem("role",res.data.role);

    navigate("/dashboard");
  };

  return (

    <div className="flex items-center justify-center h-screen bg-gradient-to-r from-blue-500 to-purple-600">

      <div className="bg-white p-8 rounded-xl shadow-lg w-96">

        <h2 className="text-2xl font-bold text-center mb-6">
          Workflow Login
        </h2>

        <input
          className="border p-2 w-full mb-4 rounded"
          placeholder="Email"
          onChange={(e)=>setEmail(e.target.value)}
        />

        <input
          type="password"
          className="border p-2 w-full mb-4 rounded"
          placeholder="Password"
          onChange={(e)=>setPassword(e.target.value)}
        />

        <button
          onClick={login}
          className="bg-blue-500 hover:bg-blue-600 text-white w-full p-2 rounded mb-4 transition"
        >
          Login
        </button>

        <p className="text-center text-sm">

          Don't have an account?{" "}

          <span
            onClick={() => navigate("/register")}
            className="text-blue-600 font-semibold cursor-pointer hover:underline"
          >
            Register
          </span>

        </p>

      </div>

    </div>
  );
};

export default Login;