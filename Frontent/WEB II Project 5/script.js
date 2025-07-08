function createNewAccount() {

    const mobile = document.getElementById("mobile").value;
    const firstName = document.getElementById("firstName").value;
    const lastName = document.getElementById("lastName").value;
    const password = document.getElementById("password").value;
    const country = document.getElementById("country").value;

    //JS Object
    const user = {
        mobile: mobile,
        firstName: firstName,
        lastName: lastName,
        password: password,
        country: country
    };

    //JS Object -> JSON
    const userJSON = JSON.stringify(user);

    // send Ajax Request
    const ajax = new XMLHttpRequest();

    ajax.open("POST", "http://localhost:8080/WEB_II_Pratical_5/CreateAccount", true);

    ajax.onreadystatechange = function () {

        if (ajax.readyState == 4) {

            if (ajax.status == 200) {

                alert("Account created successfully!");

            } else {

                alert("Please check your internet connection or try again later.");

            }

        };

    }

    ajax.send(userJSON);

}

function userLogin() {

    const mobile = document.getElementById("mobile").value;

    const password = document.getElementById("password").value;

    const loginData = {

        mobile: mobile,
        password: password
    }

    const loginJSON = JSON.stringify(loginData);

    const ajax = new XMLHttpRequest();

    ajax.onreadystatechange = function () {

        if (ajax.readyState == 4) {

            if (ajax.status == 200) {
                alert(ajax.responseText);
            } else {

                alert("User login failed!");
            }

        }

    };

    ajax.open("POST", "http://localhost:8080/WEB_II_Pratical_5/Login", true);

    ajax.send(loginJSON);

}

function loadUsers() {

    const tbody = document.getElementById("user_data_body");

    tbody.innerHTML = "";

    const ajax = new XMLHttpRequest();

    ajax.onreadystatechange = function () {

        if (ajax.readyState === 4) {

            if (ajax.status === 200) {

                try {

                    const users = JSON.parse(ajax.responseText);
                    let count = 1;

                    users.forEach((u) => {
                        const row = document.createElement("tr");
                        row.className = "hover:bg-blue-50 transition-colors";
                        row.innerHTML = `
                                    <td class="py-2 px-4 border-b">${count}</td>
                                    <td class="py-2 px-4 border-b">${u.firstName}</td>
                                    <td class="py-2 px-4 border-b">${u.lastName}</td>
                                    <td class="py-2 px-4 border-b">${u.mobile}</td>
                                    <td class="py-2 px-4 border-b">${u.country}</td>
                                `;
                        tbody.appendChild(row);
                        count++;
                    });

                } catch (err) {

                    console.error("Error parsing user data:", err);
                    alert("Error parsing data");

                }

            } else {

                alert("User data loading failed! Status: " + ajax.status);

            }

        }

    };

    ajax.open("GET", "http://localhost:8080/WEB_II_Pratical_5/AllUsers", true);

    ajax.send();

}