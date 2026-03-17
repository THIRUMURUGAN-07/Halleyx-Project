import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const Register = () => {

  const navigate = useNavigate();

  const [name,setName] = useState("");
  const [email,setEmail] = useState("");
  const [password,setPassword] = useState("");
  const [role,setRole] = useState("USER");

  const [otp,setOtp] = useState("");
  const [showOtp,setShowOtp] = useState(false);

  const register = async () => {

    await axios.post(
      "http://localhost:8080/auth/register",
      {name,email,password,role}
    );

    setShowOtp(true);
  };

  const verifyOtp = async () => {

    await axios.post(
      `http://localhost:8080/auth/verify-otp`,
      {email,otp}
    );

    localStorage.setItem("name",name);
    localStorage.setItem("role",role);

    navigate("/dashboard");
  };

  return (

    <div className="flex items-center justify-center h-screen bg-gray-100">

      <div className="bg-white p-6 rounded shadow w-96">

        <h2 className="text-xl font-bold mb-4">Register</h2>

        <input
          className="border p-2 w-full mb-3"
          placeholder="Name"
          onChange={(e)=>setName(e.target.value)}
        />

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

        <select
          className="border p-2 w-full mb-3"
          onChange={(e)=>setRole(e.target.value)}
        >

          <option value="USER">User</option>
          <option value="MANAGER">Manager</option>
          <option value="ADMIN">Admin</option>

        </select>

        <button
          onClick={register}
          className="bg-green-500 text-white w-full p-2 rounded"
        >
          Register
        </button>

      </div>

      {showOtp && (

        <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50">

          <div className="bg-white p-6 rounded w-80">

            <h3 className="text-lg font-bold mb-3">
              Enter OTP
            </h3>

            <input
              className="border p-2 w-full mb-3"
              placeholder="OTP"
              onChange={(e)=>setOtp(e.target.value)}
            />

            <button
              onClick={verifyOtp}
              className="bg-blue-500 text-white w-full p-2 rounded"
            >
              Verify OTP
            </button>

          </div>

        </div>

      )}

    </div>
  );
};

export default Register;