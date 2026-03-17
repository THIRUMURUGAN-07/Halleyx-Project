import { useNavigate } from "react-router-dom";

const Navbar = () => {

  const navigate = useNavigate();

  const logout = () => {
    localStorage.removeItem("token");
    navigate("/login");
  };

  return (

    <div className="bg-white shadow p-4 flex justify-between">

      <h1 className="text-xl font-semibold">
        Dashboard
      </h1>

      <button
        onClick={logout}
        className="bg-red-500 text-white px-4 py-2 rounded"
      >
        Logout
      </button>

    </div>
  );
};

export default Navbar;