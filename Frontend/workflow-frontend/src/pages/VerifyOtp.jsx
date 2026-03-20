import { useState } from "react";
import axios from "axios";
import { useLocation, useNavigate } from "react-router-dom";

const VerifyOtp = () => {

  const [otp, setOtp] = useState("");

  const location = useLocation();
  const navigate = useNavigate();

  const email = location.state?.email;

  const verify = async () => {

    if (!otp) {
      alert("Enter OTP");
      return;
    }

    try {

      const res = await axios.post(
        "http://localhost:8080/auth/verify-otp",
        { email, otp }
      );

      alert("Verified Successfully");

      navigate("/login");

    } catch (err) {

      alert("Invalid OTP");

    }
  };

  return (

    <div className="flex items-center justify-center h-screen bg-gray-100">

      <div className="bg-white p-6 rounded shadow w-80">

        <h2 className="text-xl font-bold mb-4 text-center">
          Verify OTP
        </h2>

        <p className="text-sm mb-3 text-center">
          OTP sent to: {email}
        </p>

        <input
          className="border p-2 w-full mb-3"
          placeholder="Enter OTP"
          onChange={(e)=>setOtp(e.target.value)}
        />

        <button
          onClick={verify}
          className="bg-blue-500 text-white w-full p-2 rounded"
        >
          Verify
        </button>

      </div>

    </div>
  );
};

export default VerifyOtp;