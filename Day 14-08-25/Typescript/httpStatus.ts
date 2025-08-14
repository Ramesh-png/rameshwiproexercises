enum httpStatus{
    OK = 100,
    NOTFOUND = 404,
    INTERNALERROR = 401,
    ACCESSDENIED = 402

}

console.log("OK:", httpStatus.OK);
console.log("Not Found:", httpStatus.NOTFOUND);
console.log("Access Denied:", httpStatus.ACCESSDENIED);
console.log("Internal Error:", httpStatus.INTERNALERROR);