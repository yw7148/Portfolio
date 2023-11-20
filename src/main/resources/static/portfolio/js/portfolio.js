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
}

async function showProjectModal(ev) {
    let projectId = ev.currentTarget.dataset.projectId;
    projectProgramsModal.modalTitle.innerHTML = `
        <span>${ev.currentTarget.dataset.projectName}</span>
        <span class="ms-2 fs-6 text-secondary">${ev.currentTarget.dataset.projectCategory}</span>
    `;
    await projectProgramsModal.launchModal(projectId);
}
