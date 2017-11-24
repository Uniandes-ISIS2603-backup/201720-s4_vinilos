/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */



package co.edu.uniandes.csw.dtos;

//~--- non-JDK imports --------------------------------------------------------

import co.edu.uniandes.csw.tiendaVinilos.entities.FeedBackEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoClienteEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.TarjetaEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.UsuarioEntity;

//~--- JDK imports ------------------------------------------------------------

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jd.arenas
 */
public class UsuarioDetailDTO extends UsuarioDTO {
    private List<TarjetaDTO>       tarjetas;
    private List<FeedBackDTO>      feeds;
    private List<PedidoClienteDTO> pedidos;
    private CarroComprasDetailDTO  carrito;

    // Constructor por defecto
    public UsuarioDetailDTO() {

        // Constructor por defecto
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public UsuarioDetailDTO(UsuarioEntity entity) {
        super(entity);

        List<TarjetaEntity> lista = entity.getTarjetas();
        List<TarjetaDTO>    list  = new ArrayList<>();

        for (TarjetaEntity tarjetaEntity : lista) {
            TarjetaDTO dto = new TarjetaDTO(tarjetaEntity);

            list.add(dto);
        }

        tarjetas = list;

        List<FeedBackEntity> lista2 = entity.getFeedBacks();
        List<FeedBackDTO>    list2  = new ArrayList<>();

        for (FeedBackEntity feedback : lista2) {
            FeedBackDTO dto = new FeedBackDTO(feedback);

            list2.add(dto);
        }

        feeds = list2;

        List<PedidoClienteEntity> lista3 = entity.getPedidos();
        List<PedidoClienteDTO>    list3  = new ArrayList<>();

        for (PedidoClienteEntity pedido : lista3) {
            PedidoClienteDTO dto = new PedidoClienteDTO(pedido);

            list3.add(dto);
        }

        pedidos = list3;
        carrito = new CarroComprasDetailDTO(entity.getCarrito());
    }

    public CarroComprasDetailDTO getCarrito() {
        return carrito;
    }

    public void setCarrito(CarroComprasDetailDTO carrito) {
        this.carrito = carrito;
    }

    public List<FeedBackDTO> getFeeds() {
        return feeds;
    }

    public void setFeeds(List<FeedBackDTO> feeds) {
        this.feeds = feeds;
    }

    public List<PedidoClienteDTO> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<PedidoClienteDTO> pedidos) {
        this.pedidos = pedidos;
    }

    public List<TarjetaDTO> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(List<TarjetaDTO> tarjetas) {
        this.tarjetas = tarjetas;
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return
     */
    @Override
    public UsuarioEntity toEntity() {
        UsuarioEntity            usuarioE = super.toEntity();
        ArrayList<TarjetaEntity> cards    = new ArrayList<>();

        if (tarjetas != null) {
            for (TarjetaDTO tarjeta : tarjetas) {
                cards.add(tarjeta.toEntity());
            }
        }

        ArrayList<FeedBackEntity> feedbacks = new ArrayList<>();

        if (feeds != null) {
            for (FeedBackDTO feed : feeds) {
                feedbacks.add(feed.toEntity());
            }
        }

        ArrayList<PedidoClienteEntity> pedidos2 = new ArrayList<>();

        if (pedidos != null) {
            for (PedidoClienteDTO pedido : pedidos) {
                pedidos2.add(pedido.toEntity());
            }
        }

        usuarioE.setPedidos(pedidos2);
        usuarioE.setTarjetas(cards);
        usuarioE.setFeedBacks(feedbacks);

        if (carrito != null) {
            usuarioE.setCarrito(carrito.toEntity());
        }

        return usuarioE;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
