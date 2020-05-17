(async () => {
    cont url = 'http://localhost:8080/gyms';
    const response = await fetch(url);
    const data = await response.json();
    console.log(data);

    const element = document.getElementById('app');
        element.innerHTML = `
            ${restaurants.map(restaurant => `
                <p>
                    ${gym.id}
                    ${gym.name}
                    ${gym.address}
                </p>
            `).join('')}
        `;

})();