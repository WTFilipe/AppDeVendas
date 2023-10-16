# AppDeVendas

O aplicativo App de Vendas simula um app simples para e-commerce. No App de Vendas está disponivel uma listagem de produtos (listagem mockada localmente, não conheço uma API que forneça dados de produtos da forma necessária. Porém, caso exista, 
a lógica para receber dados da internet está completamente isolada em um "Remote Data", sendo fácil adicionar uma lib para requests (Como o Retrofit API) e passar a receber dados verdadeiros de uma API.

Na tela inicial do aplicativo você encontrará a listagem de produtos disponíveis, sendo possível clicar em um produto para abrir mais detalhes e adicionar 1 ou mais produtos daquele tipo no carrinho. Ao adicionar itens no carrinho, uma pequena view irá 
aparecer no canto inferior da tela com o resumo (quantidade de itens e preço total) da compra. Ao lado será apresentado um botão para que o usuário possa ir para o carrinhho.

Na tela de carrinho, será apresentada uma listagem com todos os produtos selecionados, junto com a quantidade de cada item, o preço unitário de cada item, o valor total de cada item e o total da compra (somatório do valor total de todos os itens). 
O reusmo do carrinho também será apresentado no canto inferior da tela mas, dessa vez, o botão de ação irá permitir finalizar a compra. Antes da compra realmente ser finalizada, será exibido um dialog de confirmação.

Após a confirmação da compra será gerado um número de pedido, as informações sobre o pedido (número do pedido, quantidade de itens, valor unitário do item, valor total do item e valor total da compra) serão exibidas em uma listagem. Os pedidos serão exibidos,
inicialmente, em um item colapsado. Basta tocar em um item pare ver mais informações.

## Tecnologias utilizadas:
- Material Design 3- Componentes e tema da aplicação
- Hilt - Injeção de dependência
- Coil - Carregamento de imagens
- Arquitetura MVVM + Clean Architecture
- Jetpack Compose - Desenvolvimento da UI
- Navigation - Para navegação entre as telas

## Observações
- A aplicação foi pensada para usar os conceitos de temas dinâmicos do Material 3. Por conta disso, as cores do aplicativo podem ser diferentes das cores exibidas na captura de tela, pois serão baseadas na cor do papel de parede do dispositivo. Qualquer que sejam as cores, a hierarquia de cores, visibilidade e contraste estão garantidos. Caso o projeto seja executado em um dispositivo que não ofereça suporte ao Material 3, um tema padrão (cores próximas ao azul e amarelo) será apresentado


## Execução
O aplicativo já está pronto para a execução. Basta clonar o projeto e rodar no Android Studio
