(async () => {
    const url = 'http://localhost:8080/gyms';
    const response = await fetch(url);
    const restaurants = await response.json();

    const element = document.getElementById('app');
    element.innerHTML = `
        ${gyms.map(restaurant => `
            <p>
                ${gym.id}
                ${gym.name}
                ${gym.address}
            </p>
        `).join('')}
    `;
})();