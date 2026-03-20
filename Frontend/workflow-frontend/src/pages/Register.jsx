import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const Register = () => {

  const navigate = useNavigate();

  const [name,setName] = useState("");
  const [email,setEmail] = useState("");
  const [password,setPassword] = useState("");
  const [role,setRole] = useState("USER");

  const register = async () => {

  await axios.post(
    "http://localhost:8080/auth/register",
    { name, email, password, role }
  );

  alert("OTP sent to your email");

  // 👉 move to OTP page and pass email
  navigate("/verify-otp", { state: { email } });
};

  return (

    <div className="flex items-center justify-center h-screen bg-gradient-to-r from-green-400 to-blue-500">

      <div className="bg-white p-8 rounded-xl shadow-lg w-96">

        <h2 className="text-2xl font-bold text-center mb-6">
          Register
        </h2>

        <input
          className="border p-2 w-full mb-4 rounded"
          placeholder="Name"
          onChange={(e)=>setName(e.target.value)}
        />

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

        <select
          className="border p-2 w-full mb-4 rounded"
          onChange={(e)=>setRole(e.target.value)}
        >

          <option value="USER">User</option>
          <option value="MANAGER">Manager</option>
          <option value="ADMIN">Admin</option>
          <option value="HR">HR</option>

        </select>

        <button
          onClick={register}
          className="bg-green-500 hover:bg-green-600 text-white w-full p-2 rounded mb-4 transition"
        >
          Register
        </button>

        <p className="text-center text-sm">

          Already registered?{" "}

          <span
            onClick={() => navigate("/login")}
            className="text-blue-600 font-semibold cursor-pointer hover:underline"
          >
            Login
          </span>

        </p>

      </div>

    </div>
  );
};

export default Register;