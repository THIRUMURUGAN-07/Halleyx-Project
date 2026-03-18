import AdminDashboard from "./AdminDashboard";
import UserDashboard from "./UserDashboard";
import ManagerDashboard from "./ManagerDashboard";

const DashboardRouter = () => {

  const role = localStorage.getItem("role");

  if(role === "ADMIN"){
    return <AdminDashboard/>
  }

  if(role === "MANAGER"){
    return <ManagerDashboard/>
  }

  return <UserDashboard/>
}

export default DashboardRouter;