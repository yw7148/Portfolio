class ProjectProgramsModal {
    #modalSelector = "#programsModal"
    #bootstrapModal

    constructor(selector) {
        if(selector) this.#modalSelector = selector;

        this.#bootstrapModal = new bootstrap.Modal(this.#modalSelector);
        this.modalElement.querySelector('.btn-close').addEventListener('click', ev => {
            this.#bootstrapModal.hide()
        })
    }

    async launchModal(projectId) {
        let response = await fetch(
            `portfolio/programs?projectId=${projectId}`, { method: "GET" }
        );
        let programs = await response.json();

        this.modalBody.textContent = '';
        programs.forEach(program => {
            let programElement = document.createElement('a');
            programElement.className = 'btn btn-outline-dark';
            programElement.innerHTML = `
                <div class="fs-3 ${program.icon}"></div>
                <div class="fs-5">${program.name}<div>
            `
            programElement.setAttribute('href', program.link);
            programElement.setAttribute('target', '_blank');
            this.modalBody.append(programElement);
        })

        this.#bootstrapModal.show();
    }

    get modalElement() {
        return document.querySelector(this.#modalSelector);
    }

    get modalTitle() {
        return this.modalElement.querySelector('.modal-title')
    }

    get modalBody() {
        return this.modalElement.querySelector('.modal-body')
    }
}

let portfolioProjects;
let projectProgramsModal = new ProjectProgramsModal();
window.addEventListener('load', setEvents)

function setEvents() {
    document.getElementById('portfolio').querySelectorAll('.portfolio-box').forEach(box => {
        box.addEventListener('click', showProjectModal)
    })
    document.getElementById('submitButton').addEventListener('click', async function(ev) {
        let name = document.getElementById('name').value;
        let email = document.getElementById('email').value;
        let message = document.getElementById('message').value;

        let response = await fetch('/portfolio/contact', {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                'name': name,
                'email': email,
                'message': message
            })
        });

        let result = await response.json();
        let success = response.ok;
        if(success && result) {
            Swal.fire({
                icon: "success",
                title: "Success",
                text: "Successfully sent message!"
            });
        }

        ev.preventDefault();
    })
}

async function showProjectModal(ev) {
    let projectId = ev.currentTarget.dataset.projectId;
    projectProgramsModal.modalTitle.innerHTML = `
        <span>${ev.currentTarget.dataset.projectName}</span>
        <span class="ms-2 fs-6 text-secondary">${ev.currentTarget.dataset.projectCategory}</span>
    `;
    await projectProgramsModal.launchModal(projectId);
}
