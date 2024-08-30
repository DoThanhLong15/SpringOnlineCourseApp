document.addEventListener('DOMContentLoaded', () => {

    const tagInput = document.getElementById('tagInput');
    const tagContainer = document.getElementById('tagContainer');
    const searchContainer = document.getElementById('search-container');

    tagInput.addEventListener('keydown', (event) => {
        let data = tagSearch(tagInput.value);
    });

    // Optional: Allow removing tags by clicking on them
    tagContainer.addEventListener('click', (event) => {
        if (event.target.classList.contains('tag')) {
            event.target.remove();
        }
    });
});


const tagSearch = async (kw) => {
    try {
        const response = await fetch(`/api/tags/?q=${kw}`);
        const data = await response.json();
        return data; // Adjust based on your API response format
    } catch (error) {
        console.error('Error fetching tags:', error);
        return [];
    }
};

const renderList = (items) => {
//    list.innerHTML = ''; // Clear the list
//    items.forEach(item => {
//        const listItem = document.createElement('li');
//        listItem.className = 'list-group-item';
//        listItem.textContent = item;
//        list.appendChild(listItem);
//    });
};

const addTag = (tag, tagContainer, tagInput) => {
    if (tag.trim() === '')
        return;

    const tagElement = document.createElement('span');
    tagElement.classList.add('tag');
    tagElement.textContent = tag;

    const removeButton = document.createElement('span');
    removeButton.classList.add('remove-tag');
    removeButton.textContent = 'x';
    removeButton.onclick = () => tagElement.remove();

    tagElement.appendChild(removeButton);
    tagContainer.appendChild(tagElement);
    tagInput.value = ''; // Clear input field
};