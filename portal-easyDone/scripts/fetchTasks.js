async function getUsers() {
    let url = 'http://localhost:4002/api/v1/tasks/';
    try {
        let res = await fetch(url);
        return await res.json();
    } catch (error) {
        console.log(error);
    }
}

async function renderUsers() {
    let users = await getUsers();
    let html = '';
    users.forEach(user => {
        let htmlSegment = `<div class="col">
        <div class="card text-white bg-dark mb-3">
          <div class="card-body">
            <h5 class="card-title">${user.taskTitle}</h5>
            <p class="card-text">Location :${user.taskLocation} <br>

            Description : ${user.taskDescr} <br>
            Time : ${user.taskTime} <br>
            Payment : ${user.payment} <br>
            Contact : ${user.userContact}
            </p>
          </div>
        </div>
      </div>`;

        html += htmlSegment;
    });

    let container = document.getElementById('task-grid');
    container.innerHTML = html;
}

renderUsers();