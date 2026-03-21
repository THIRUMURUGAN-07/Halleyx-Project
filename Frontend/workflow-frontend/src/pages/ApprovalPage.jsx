import Layout from "../components/Layout";
import { useEffect, useState } from "react";
import axios from "axios";

const Approval = () => {
  const [executions, setExecutions] = useState([]);

  const token = localStorage.getItem("token");

  // LOAD PENDING REQUESTS
  const loadExecutions = async () => {
    try {
      const res = await axios.get(
        "http://localhost:8080/approvals/pending", //  create this API
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        },
      );
      console.log(res.data);
      setExecutions(res.data);
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    loadExecutions();
  }, []);

  // APPROVE
  const approve = async (id) => {
    try {
    // const res =  await axios.post(
    //     `http://localhost:8080/approvals/approve`,
    //     {id},
    //     {
    //       headers: {
    //         Authorization: `Bearer ${token}`,
    //       },
    //     },
    //   );
    //   console.log(res.data);
      alert("Approved");
      setExecutions([]);
    
    } catch (err) {
      console.log(err);
      alert("Error approving");
    }
  };

  // REJECT
  const reject = async (id) => {
    try {
      // await axios.post(
      //   `http://localhost:8080/approvals/reject`,
      //   {id},
      //   {
      //     headers: {
      //       Authorization: `Bearer ${token}`,
      //     },
      //   },
      // );

      alert("Rejected");
      
      setExecutions([]);
    } catch (err) {
      console.log(err);
      alert("Error rejecting");
    }
  };

  return (
    <Layout>
      <h2 className="text-2xl font-bold mb-6">Approval Requests</h2>

      <table className="w-full bg-white shadow rounded">
        <thead className="bg-gray-200">
          <tr>
            <th className="p-3">Workflow</th>
            <th className="p-3">Details</th>

            <th className="p-3">Action</th>
          </tr>
        </thead>

        <tbody>
          {executions.map((exec) => {
            let details = "";

            if (exec.days) {
              details = `${exec.days} days`;
            } else if (exec.amount) {
              details = `₹ ${exec.amount}`;
            }

            return (
              <tr key={exec.id} className="border-t text-center">
                <td className="p-3">{exec.name || exec.workflowId}</td>

                <td className="p-3">{details}</td>
                {/* <td className="p-3">{exec.userName}</td> */}

                <td className="p-3 space-x-2">
                  <button
                    onClick={() => approve(exec.id)}
                    className="bg-green-500 text-white px-3 py-1 rounded"
                  >
                    Approve
                  </button>

                  <button
                    onClick={() => reject(exec.id)}
                    className="bg-red-500 text-white px-3 py-1 rounded"
                  >
                    Reject
                  </button>
                </td>
              </tr>
            );
          })}
        </tbody>
      </table>
    </Layout>
  );
};

export default Approval;
