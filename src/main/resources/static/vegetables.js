function vege() {
    if (document.getElementById("vegetables")) {
        window.location.reload();
    } else {
        sessionStorage.removeItem("Authorization")
        console.log(window.sessionStorage.getItem("Authorization"))
        $.ajax({
            type: 'GET',
            url: '/vegetables',
            success: function (response) {
                let accessToken = response["accessToken"];
                console.log(accessToken);
                window.sessionStorage.setItem("Authorization", accessToken)
                console.log(window.sessionStorage.getItem("Authorization"))
                let tempHtml = `<button onclick="vegetables()" id="vegetables"> 채소 판매 목록</button>
                                                <div id="vege-list"></div>`;
                $('#cards-box').append(tempHtml);
            },
            error: function () {
                alert("실패")
            }
        })
    }
}

function vegetables() {
    if (!document.getElementById("vegetables0")) {
        $.ajax({
            type: 'GET',
            url: '/vegetables/lists',
            headers: {"Authorization": sessionStorage.getItem("Authorization")},
            success: function (response) {
                console.log(response)
                for (let i = 0; i < response.length; i++) {
                    console.log(response[i]);
                    let tempHtml =
                        `<button onclick="vegetables${i}()" class="vegetables" id="vegetables${i}">${response[i]}</button>`
                    $('#vege-list').append(tempHtml);
                }

            }
        })
    }
}

function vegetables0() {
    if (!document.getElementById("vege0")) {
        let name = document.getElementById("vegetables0").textContent
        $.ajax({
            type: 'GET',
            url: `vegetables/products?name=${name}`,
            headers: {"Authorization": sessionStorage.getItem("Authorization")},
            success: function (response) {
                let tempHtml =
                    `<p id="vege0">${response["name"]} : ${response["price"]}</p>`
                $('#vege-list').append(tempHtml);
            }
        })
    }
}

function vegetables1() {
    if (!document.getElementById("vege1")) {
        let name = document.getElementById("vegetables1").textContent
        $.ajax({
            type: 'GET',
            url: `vegetables/products?name=${name}`,
            headers: {"Authorization": sessionStorage.getItem("Authorization")},
            success: function (response) {
                let tempHtml =
                    `<p id="vege1">${response["name"]} : ${response["price"]}</p>`
                $('#vege-list').append(tempHtml);
            }
        })
    }
}

function vegetables2() {
    if (!document.getElementById("vege2")) {
        let name = document.getElementById("vegetables2").textContent
        $.ajax({
            type: 'GET',
            url: `vegetables/products?name=${name}`,
            headers: {"Authorization": sessionStorage.getItem("Authorization")},
            success: function (response) {
                let tempHtml =
                    `<p id="vege2">${response["name"]} : ${response["price"]}</p>`
                $('#vege-list').append(tempHtml);
            }
        })
    }
}

function vegetables3() {
    if (!document.getElementById("vege3")) {
        let name = document.getElementById("vegetables3").textContent
        $.ajax({
            type: 'GET',
            url: `vegetables/products?name=${name}`,
            headers: {"Authorization": sessionStorage.getItem("Authorization")},
            success: function (response) {
                let tempHtml =
                    `<p id="vege3">${response["name"]} : ${response["price"]}</p>`
                $('#vege-list').append(tempHtml);
            }
        })
    }
}