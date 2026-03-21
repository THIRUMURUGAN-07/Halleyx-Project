import { useNavigate } from "react-router-dom";

const Navbar = () => {

  const navigate = useNavigate();

  const logout = () => {
    localStorage.clear();
    navigate("/login");
  };

  return (

    <div className="fixed top-0 left-0 w-full bg-white shadow px-6 py-3 flex justify-between items-center z-50">

      <h2 className="text-lg font-bold">
        Dashboard
      </h2>

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