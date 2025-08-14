var httpStatus;
(function (httpStatus) {
    httpStatus[httpStatus["OK"] = 100] = "OK";
    httpStatus[httpStatus["NOTFOUND"] = 404] = "NOTFOUND";
    httpStatus[httpStatus["INTERNALERROR"] = 401] = "INTERNALERROR";
    httpStatus[httpStatus["ACCESSDENIED"] = 402] = "ACCESSDENIED";
})(httpStatus || (httpStatus = {}));
console.log("OK:", httpStatus.OK);
console.log("Not Found:", httpStatus.NOTFOUND);
console.log("Access Denied:", httpStatus.ACCESSDENIED);
console.log("Internal Error:", httpStatus.INTERNALERROR);
