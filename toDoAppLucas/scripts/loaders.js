    function renderizarSkeletons(cantidad, contenedor) {
            
                const contenedorTareas = document.querySelector(contenedor);
                
                
                const skeletons = Array.from({ length: cantidad });
                
                
                skeletons.forEach(() => {
                
                const template = `
                <li class="skeleton-container ${contenedor.replace(".", "")}-child">
                    <div class="skeleton-card">
                    <p class="skeleton-text"></p>
                    <p class="skeleton-text"></p>
                    </div>
                </li>
                `;
                
                contenedorTareas.innerHTML += template;
                });
                }

     function removerSkeleton(contenedor) {
                // Seleccionamos el contenedor
                const contenedorTareas = document.querySelector(contenedor);
                
                // Seleccionamos todos los skeletons dentro de ese contenedor
                const skeletons = document.querySelectorAll(`${contenedor}-child`);
                
                // Iteramos sobre la lista de skeletons y removemos cada uno de ellos
                // de dicho contenedor
                skeletons.forEach((skeleton) => contenedorTareas.removeChild(skeleton));
                }
            

            