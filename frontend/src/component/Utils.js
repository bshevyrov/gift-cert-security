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

export const sendRequestCreate = async (arr, fields) => {
    return await fetch("/api/v1/gifts/", {
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
                "tags": arr.map(tagValue => ({name: tagValue.value})),
            }),
        }
    );

}
export const sendRequestUpdate = async (arr, fields) => {
    return await fetch("/api/v1/gifts/"+fields["id"].valueOf(), {
            method: "PATCH",
            headers: {
                "content-type": "application/json",
                "Authorization": "Bearer " + localStorage.getItem("token"),
            },
            body: JSON.stringify({
                "name": fields["name"].valueOf(),
                "description": fields["description"].valueOf(),
                "price": fields["price"].valueOf(),
                "duration": fields["duration"].valueOf(),
                "tags": arr.map(tagValue => ({name: tagValue.value})),
            }),
        }
    );

}


export const sendTagRequest = async () => {

    const response = await fetch("api/v1/tags?size=9999999&sort=name", {
        method: "GET",
        headers: {
            "content-type": "application/json",
        },
    })
    return await response.json();

}
export const sendGiftFindRequest = async (id) => {

    const response = await fetch("api/v1/gifts/"+id, {
        method: "GET",
        headers: {
            "content-type": "application/json",
        },
    })
    return await response.json();

}
export const sendRemoveRequest = async (id)=> {
    const response = await fetch("/api/v1/gifts/" + id, {
        method: "DELETE",

        headers: {
            "content-type": "application/json",
            "accept": "application/json",
            "Authorization": "Bearer " + localStorage.getItem("token").valueOf(),

        },
    })
//Todo error
    const responseCode = response.status;

    if (responseCode < 300) {
        window.location.reload();
    } else {
        const body = await response.json();
        return body;
    }
}
export const sendSearchTagRequest = async (arr)=> {
    const response = await fetch("/api/v1/gifts/search/tag", {
            method: "POST",
            headers: {
                "content-type": "application/json",
            },
            body: JSON.stringify(
                arr.map(tagValue => ({name: tagValue.value}))
            ),
        }
    );
    return await response.json();
}
export const sendSearchNameOrDesrRequest = async (query)=> {
    const response = await fetch("/api/v1/gifts/search/query", {
            method: "POST",
            headers: {
                "content-type": "application/json",
            },
            body: JSON.stringify({
                query: query
            }),
        }
    );
    return await response.json();
}

