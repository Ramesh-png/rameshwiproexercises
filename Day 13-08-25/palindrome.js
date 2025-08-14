function checkPalindrome() {
    let str = document.getElementById("word").value;
    let cleanStr = str.toLowerCase().replace(/[^a-z0-9]/g, ""); 
    let reversed = cleanStr.split("").reverse().join("");
    
    if (cleanStr === reversed) {
        document.getElementById("result").innerHTML = "✅ It's a palindrome!";
    } else {
        document.getElementById("result").innerHTML = "❌ Not a palindrome.";
    }
}