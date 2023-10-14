package com.filipeoliveira.appdevendas.data.remote

import com.filipeoliveira.appdevendas.data.remote.model.ItemRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.math.BigDecimal
import javax.inject.Inject

class SalesRemoteDataImpl @Inject constructor() : SalesRemoteData {

    override suspend fun getAvailableItemsList(): Flow<List<ItemRemote>> = flow {
        emit(fakeItemList)
    }

}

private val fakeItemList = listOf(
    ItemRemote(
        name = "Carrinho de controle remoto",
        description = "Carrinho controlado por controle sem fio via Wifi. " +
                "Controle seu carrinho com até 50 metros de distância e alcance até 5 km/h",
        value = BigDecimal.valueOf(307.74),
        imageURL = "https://m.media-amazon.com/images/I/61CYnxI+WnL._AC_SX522_.jpg",
        sku = "1"
    ),
    ItemRemote(
        name = "Pista Hot Wheels - Parque dos Tubarões",
        description = "Desafie o Mega Tubarão e passe por dentro de sua boca, mas seja rápido o suficiente para não levar uma dentada!",
        value = BigDecimal.valueOf(110.32),
        imageURL = "https://photos.enjoei.com.br/parque-dos-tubaroes/1200xN/czM6Ly9waG90b3MuZW5qb2VpLmNvbS5ici9wcm9kdWN0cy80ODc4ODYvNjg1YTUyYmVjYmUwNDM4MmI2OTA5OWM0NWRkZWFkOGUuanBn",
        sku = "2"
    ),
    ItemRemote(
        name = "Barbie - Casa dos Sonhos",
        description = "Desça de escorrega, tome banho na piscina, se vista e divirta-se com Barbie e suas amigas!",
        value = BigDecimal.valueOf(1100.99),
        imageURL = "https://m.media-amazon.com/images/I/91NQjNxnF9L._AC_SX679_.jpg",
        sku = "3"
    ),
    ItemRemote(
        name = "Gugu Equilibrista",
        description = "Brinque com o Gugu! Desafie seus amigos e veja quem consegue adicionar mais pesos sem derrubar o Gugu! Quem derrubar primeiro, perde",
        value = BigDecimal.valueOf(110.49),
        imageURL = "https://d1o6h00a1h5k7q.cloudfront.net/imagens/img_m/14676/6704725_2.jpgg",
        sku = "4"
    ),ItemRemote(
        name = "Máquina de Sorvete da Eliana",
        description = "Junte seus amigos, escolham um sabor de sorvete e aproveitem uma maravilhosa (e refrescante) sobremesa juntos!",
        value = BigDecimal.valueOf(149.17),
        imageURL = "https://extra.globo.com/incoming/24753104-acf-6a8/w448/brinquedos-eliana-filhos-2.jpg",
        sku = "5"
    ),
)