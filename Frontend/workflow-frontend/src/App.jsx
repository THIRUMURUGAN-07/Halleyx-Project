import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";

import Dashboard from "./pages/Dashboard";
import WorkflowList from "./pages/WorkflowList";
import Login from "./pages/Login";
import Register from "./pages/Register";
import ProtectedRoute from "./routes/ProtectedRoute";

function App() {
  return (
    <BrowserRouter>
      <Routes>

        {/* Default route */}
        <Route path="/" element={<Navigate to="/dashboard" />} />

        {/* Login */}
        <Route path="/login" element={<Login />} />

        {/* Register */}

        <Route path="/Register" element={<Register/>}/>

        {/* Dashboard */}
        <Route
          path="/dashboard"
          element={
            <ProtectedRoute>
              <Dashboard />
            </ProtectedRoute>
          }
        />

        {/* Workflows */}
        <Route
          path="/workflows"
          element={
            <ProtectedRoute>
              <WorkflowList />
            </ProtectedRoute>
          }
        />

      </Routes>
    </BrowserRouter>
  );
}

export default App;