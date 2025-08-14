document.getElementById("name").innerHTML="Exercise 2";



   function lengthcount() {
      const text = document.getElementById("myText").value;
      const length = text.length;
      document.getElementById("result").textContent = "Length: " + length;
    }