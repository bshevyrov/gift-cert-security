export const sendRequest = async (methodType) => {
    function preparedUrl() {
        let url = "/api/v1/gifts?"
            + "page=" + (localStorage.getItem("currentPage") || "0") + "&"
            + "sort=" + (localStorage.getItem("sort") || "createdDate,desc") + "&"
            + "size=" + (localStorage.getItem("size") || "10");
        return url;
    }

    const response = await fetch(preparedUrl(), {
        method: methodType,
        headers: {
            "content-type": "application/json",
        },
    })
    return await response.json();

}
const createTagBodyArr =(arr)=> {
    let qqq= arr.map(tagValue => ({name: tagValue.value}));
console.log(qqq);
return qqq;
}
export const sendRequestCreate = async (arr, fields) => {
    return await fetch("/api/v1/gifts", {
            method: "POST",
            headers: {
                "content-type": "application/json",
                "Authorization": "Bearer " + localStorage.getItem("token"),
            },
            body: JSON.stringify({
                "name": fields["name"].valueOf(),
                "description": fields["description"].valueOf(),
                "price": fields["price"].valueOf(),
                "duration": fields["duration"].valueOf(),
                "tags": createTagBodyArr(arr),
            }),
        }
    );

}


export const sendTagRequest = async (methodType) => {

    const response = await fetch("api/v1/tags", {
        method: methodType,
        headers: {
            "content-type": "application/json",
        },
    })
    return await response.json();

}

export const sendSearchRequest = async (arr)=> {
console.log("in");
    const response = await fetch("/api/v1/gifts/search", {
            method: "POST",
            headers: {
                "content-type": "application/json",
                "Authorization": "Bearer " + localStorage.getItem("token"),
            },
            body: JSON.stringify({
                "tags": createTagBodyArr(arr),
            }),
        }
    );
    return await response.json();
}