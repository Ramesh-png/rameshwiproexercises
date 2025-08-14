    const people = [
      { name: "ram", age: 17, city: "India" },
      { name: "pavan", age: 22, city: "USA" },
      { name: "sai", age: 19, city: "Los Angeles" },
      { name: "ravi", age: 16, city: "Italy" },
      { name: "pavi", age: 30, city: "Myhouse" }
    ];

    const updatedPeople = people.map(person => {
      person.status = person.age >= 18 ? "Adult" : "Minor";
      return person;
    });

    const output = updatedPeople.map(p => `${p.name} (${p.age}) - ${p.city} - ${p.status}`).join("<br>");
    document.getElementById("result").innerHTML = output;