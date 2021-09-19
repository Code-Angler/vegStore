function fruit() {
    if (document.getElementById("fruits")) {
        window.location.reload();
    } else {
        sessionStorage.removeItem("Authorization")
        console.log(window.sessionStorage.getItem("Authorization"))
        $.ajax({
            type: 'GET',
            url: '/fruits',
            success: function (response) {
                let accessToken = response["accessToken"];
                console.log(accessToken);
                window.sessionStorage.setItem("Authorization", accessToken)
                console.log(window.sessionStorage.getItem("Authorization"))
                let tempHtml = `<button onclick="fruits()" id="fruits"> 과일 판매 목록</button>
                                                <div id="fruit-list"></div>`;
                $('#cards-box').append(tempHtml);
            },
            error: function () {
                alert("실패")
            }
        })
    }
}


function fruits() {
    if (!document.getElementById("fruits0")) {
        $.ajax({
            type: 'GET',
            url: '/fruits/lists',
            headers: {"Authorization": sessionStorage.getItem("Authorization")},
            success: function (response) {
                console.log(response)
                for (let i = 0; i < response.length; i++) {
                    console.log(response[i]);
                    let tempHtml =
                        `<button onclick="fruits${i}()" class="fruits" id="fruits${i}">${response[i]}</button>`
                    $('#fruit-list').append(tempHtml);
                }
            }
        })

    }
}

function fruits0() {
    if (!document.getElementById("fruit0")) {
        let name = document.getElementById("fruits0").textContent
        $.ajax({
            type: 'GET',
            url: `fruits/products?name=${name}`,
            headers: {"Authorization": sessionStorage.getItem("Authorization")},
            success: function (response) {
                let tempHtml =
                    `<p id="fruit0">${response["name"]} : ${response["price"]}</p>`
                $('#fruit-list').append(tempHtml);
            }
        })
    }
}

function fruits1() {
    if (!document.getElementById("fruit1")) {
        let name = document.getElementById("fruits1").textContent
        $.ajax({
            type: 'GET',
            url: `fruits/products?name=${name}`,
            headers: {"Authorization": sessionStorage.getItem("Authorization")},
            success: function (response) {
                let tempHtml =
                    `<p id="fruit1">${response["name"]} : ${response["price"]}</p>`
                $('#fruit-list').append(tempHtml);
            }
        })
    }
}

function fruits2() {
    if (!document.getElementById("fruit2")) {
        let name = document.getElementById("fruits2").textContent
        $.ajax({
            type: 'GET',
            url: `fruits/products?name=${name}`,
            headers: {"Authorization": sessionStorage.getItem("Authorization")},
            success: function (response) {
                let tempHtml =
                    `<p id="fruit2">${response["name"]} : ${response["price"]}</p>`
                $('#fruit-list').append(tempHtml);
            }
        })
    }
}

function fruits3() {
    if (!document.getElementById("fruit3")) {
        let name = document.getElementById("fruits3").textContent
        $.ajax({
            type: 'GET',
            url: `fruits/products?name=${name}`,
            headers: {"Authorization": sessionStorage.getItem("Authorization")},
            success: function (response) {
                let tempHtml =
                    `<p id="fruit3">${response["name"]} : ${response["price"]}</p>`
                $('#fruit-list').append(tempHtml);
            }
        })
    }
}