angular.module('MinhaTelaApp', ['snk'])
    // filtro startFrom
    .filter('startFrom', function () {
        return function (input, start) {
            if (!input || !input.length) return [];
            start = +start; // converte para número
            return input.slice(start);
        }
    }).controller('MinhaTelaController', ['$scope', '$location', '$window', 'Criteria', 'MessageUtils', 'SkApplication', 'ObjectUtils', 'ServiceProxy',
        function ($scope, $location, $window, Criteria, MessageUtils, SkApplication, ObjectUtils, ServiceProxy) {

            let self = this;

            ObjectUtils.implements(self, IDynaformInterceptor);
            ObjectUtils.implements(self, IFormInterceptor);
            ObjectUtils.implements(self, IDatagridInterceptor);

            self.init = init;
            //self.onDynaformLoad = onDynaformLoad;
            let criteria = new CriteriaProvider();
            criteria.getCriteria = getCriteria;

            self._dataset;
            self._dynaform;

            function init() {
                var param = {};
                console.log("Chamando serviço: ListarRACSP.listar");
                ServiceProxy.callService("rac@ListarRACSP.listar", param).then(function (response) {
                    self._dataset = ObjectUtils.getProperty(response, 'responseBody.atendimentos');

                    // Remove o loader aqui, depois que os dados estão prontos
                    removeLoader()

                }).catch(function (error) {
                    MessageUtils.showInfo('Erro ao carregar dados: ' + JSON.stringify(error));

                    // Também remove loader em caso de erro
                    const loader = document.getElementById("loading");
                    if (loader) {
                        loader.style.transition = "opacity 0.5s";
                        loader.style.opacity = "0";
                        setTimeout(() => loader.remove(), 500);
                    }
                });
            }

            function removeLoader(){
                const loader = document.getElementById("loading");
                    if (loader) {
                        loader.style.transition = "opacity 0.5s";
                        loader.style.opacity = "0";
                        setTimeout(() => loader.remove(), 500);
                    }

            }

            self.paginaAtual = 1; // página inicial
            self.itensPorPagina = 10; // quantidade de itens por página





            self.abaAtiva = 'listar';
            self.direcao = 'direita'; // ou 'esquerda'

            self.irPara = function (novaAba) {
                self.direcao = novaAba === 'incluir' ? 'direita' : 'esquerda';
                self.abaAtiva = novaAba;
            };

            self.estaAtiva = function (aba) {
                return self.abaAtiva === aba;
            };

            // Timeline steps configuration
            self.steps = [
                { number: 1, label: 'Solicitação' },
                { number: 2, label: 'Qualidade' },
                { number: 3, label: 'Aprovações' },
                { number: 4, label: 'Financeiro' }
            ];

            self.activeStep = 1; // Default to first step
            self.progressWidth = 0; // Initial progress bar width

            self.setActiveStep = function (stepNumber) {
                self.activeStep = stepNumber;
                // Update progress bar width (75% for 3 steps, adjusted for 4 steps)
                self.progressWidth = ((stepNumber - 1) / (self.steps.length - 1)) * 75;
            };

            document.querySelectorAll('.btn-dropdown').forEach(button => {
                button.addEventListener('click', function (e) {
                    e.stopPropagation(); // evita fechar ao clicar no botão
                    const menu = this.nextElementSibling;
                    menu.classList.toggle('hidden');
                });
            });

            document.addEventListener('click', function (e) {
                document.querySelectorAll('.dropdown-menu').forEach(menu => {
                    menu.classList.add('hidden');
                });
            });


            function readPermissions() {
                var authData = SkApplication.instance().getAuthorizationData();

            }

            // function onDynaformLoad(dynaform, dataset) {
            //     self._dataset = dataset;
            //     self._dynaform = dynaform;
            //     self._dataset.addCriteriaProvider(criteria);
            // }

            function getCriteria() {
                let criteria = 8();
                return criteria
            }

            self.nome = "Antonio";

            // Controle do menu dropdown
            self.menuAberto = {}; // Objeto para rastrear qual menu está aberto

            self.toggleMenu = function (index) {
                // Alterna o estado do menu para a linha específica
                self.menuAberto[index] = !self.menuAberto[index];
                // Fecha outros menus abertos
                Object.keys(self.menuAberto).forEach(key => {
                    if (key != index) self.menuAberto[key] = false;
                });
            };

            self.modalAberto = false;
            self.textoModal = '';

            self.abrirModal = function (texto) {
                self.textoModal = texto;
                self.modalAberto = true
            }

            self.fecharModal = function () {
                self.modalAberto = false;
                self.textoModal = '';
            }






        }])




