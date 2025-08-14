    const employees = [
      { name: "Ram", role: "Manager", empId: "M001", salary: 80000 },
      { name: "Jay", role: "Developer", empId: "D001", salary: 60000 },
      { name: "Harsha", role: "QA", empId: "Q001", salary: 50000 },
      { name: "Pavan", role: "Manager", empId: "M002", salary: 75000 },
      { name: "Siva", role: "Developer", empId: "D002", salary: 70000 }
    ];

    const totalManagerSalary = employees
      .filter(employee => employee.role === "Manager")
      .reduce((total, manager) => total + manager.salary, 0);

    document.getElementById("result").textContent = "Total salary of Managers: ₹" + totalManagerSalary;