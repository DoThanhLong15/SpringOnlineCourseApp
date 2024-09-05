const tagInput = document.getElementById('tagInput');
const tagContainer = document.getElementById('tagContainer');
const searchContainer = document.getElementById('search-container');


document.addEventListener('DOMContentLoaded', () => {

    tagInput.addEventListener('keyup', async (event) => {
        let res = await tagSearch(tagInput.value);
        if (res.data !== null && res.data !== []) {
            renderList(res.data);
        }
    });
});


const tagSearch = async (kw) => {
    let url = `http://localhost:8080/SpringOnlineCourseApp/api/tags/?q=${kw}`;

    try {
        const response = await fetch(url);
        if (!response.ok) {
            throw new Error(`Response status: ${response.status}`);
        }
        const json = await response.json();

        return json;
    } catch (error) {
        console.error(error.message);
        return [];
    }
};

const renderList = (items) => {
    searchContainer.innerHTML = ''; // Clear the list
    items.forEach(item => {
        searchContainer.innerHTML += `
            <li class="list-group-item" id="tag-${item.id}" 
                onClick="addTag('${item.id}','${item.name}')">
                    ${item.name}
            </li>
        `;
    });
};

const addTag = (id, tag) => {
    if (tag.trim() === '')
        return;

    const tagElement = document.createElement('span');
    tagElement.classList.add(`tag-${id}`);
    tagElement.classList.add(`tag`);
    tagElement.textContent = tag;

    const removeButton = document.createElement('span');
    removeButton.classList.add('remove-tag');
    removeButton.textContent = 'x';
    removeButton.onclick = () => {
        let courseTag = document.getElementsByClassName(`tag-${id}`);

        while (courseTag.length > 0) {
            courseTag[0].remove();
        }
    };

    tagElement.appendChild(removeButton);
    tagContainer.appendChild(tagElement);
    addTagForm(id, tag);

    tagInput.value = '';
    searchContainer.innerHTML = '';
};