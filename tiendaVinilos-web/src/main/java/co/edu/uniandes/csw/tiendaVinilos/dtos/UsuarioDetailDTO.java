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
package co.edu.uniandes.csw.tiendaVinilos.dtos;
import co.edu.uniandes.csw.tiendaVinilos.entities.FeedBackEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.PedidoClienteEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.TarjetaEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.UsuarioEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.ViniloEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jd.arenas
 */
public class UsuarioDetailDTO extends UsuarioDTO {

    private ArrayList<TarjetaDTO> tarjetas;
    
    private ArrayList<FeedBackDTO> feeds;
    
    private ArrayList<PedidoClienteDTO> pedidos;
    
    private ArrayList<ViniloDTO> carrito;

    public ArrayList<ViniloDTO> getCarrito() {
        return carrito;
    }

    public void setCarrito(ArrayList<ViniloDTO> carrito) {
        this.carrito = carrito;
    }
    
    

    public ArrayList<FeedBackDTO> getFeeds() {
        return feeds;
    }

    public void setFeeds(ArrayList<FeedBackDTO> feeds) {
        this.feeds = feeds;
    }

    public ArrayList<PedidoClienteDTO> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<PedidoClienteDTO> pedidos) {
        this.pedidos = pedidos;
    }

    
    public ArrayList<TarjetaDTO> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(ArrayList<TarjetaDTO> tarjetas) {
        this.tarjetas = tarjetas;
    }
    
    
    
    /**
     * Constructor por defecto
     */
    public UsuarioDetailDTO() {
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public UsuarioDetailDTO(UsuarioEntity entity) {
        super(entity);
        List<TarjetaEntity> lista= entity.getTarjetas();
        List<TarjetaDTO> list= new ArrayList<>();
        for (TarjetaEntity tarjetaEntity : lista) {
            TarjetaDTO dto= new TarjetaDTO(tarjetaEntity);
            list.add(dto);
        }
        tarjetas= (ArrayList<TarjetaDTO>)list;
        List<FeedBackEntity> lista2= entity.getFeedBacks();
        List<FeedBackDTO> list2= new ArrayList<>();
        for (FeedBackEntity feedback : lista2) {
            FeedBackDTO dto= new FeedBackDTO(feedback);
            list2.add(dto);
        }
        feeds= (ArrayList<FeedBackDTO>)list2;

        List<PedidoClienteEntity> lista3= entity.getPedidos();
        List<PedidoClienteDTO> list3= new ArrayList<>();
        for (PedidoClienteEntity pedido : lista3) {
            PedidoClienteDTO dto= new PedidoClienteDTO(pedido);
            list3.add(dto);
        }
        pedidos= (ArrayList<PedidoClienteDTO>)list3;
        
        List<ViniloEntity> lista4= entity.getCarroCompras();
        List<ViniloDTO> list4= new ArrayList<>();
        for (ViniloEntity vinilo : lista4) {
            ViniloDTO dto= new ViniloDTO(vinilo);
            list4.add(dto);
        }
        carrito= (ArrayList<ViniloDTO>)list4;
        
        
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    @Override
    public UsuarioEntity toEntity() {
        UsuarioEntity UsuarioE = super.toEntity();
        ArrayList<TarjetaEntity> cards=new ArrayList<>();
        if(tarjetas!=null)
        {
        for (TarjetaDTO tarjeta: tarjetas) {
            cards.add(tarjeta.toEntity());
        }
        }
        ArrayList<FeedBackEntity> feedbacks=new ArrayList<>();
        if(feeds!=null){
        for (FeedBackDTO feed: feeds) {
            feedbacks.add(feed.toEntity());
        }
        }
        
        ArrayList<PedidoClienteEntity> pedidos2=new ArrayList<>();
        if(pedidos!=null){
        for (PedidoClienteDTO pedido: pedidos) {
            pedidos2.add(pedido.toEntity());
        }
        }
        ArrayList<ViniloEntity> vinilos=new ArrayList<>();
        if(carrito!=null){
        for (ViniloDTO vinilo: carrito) {
            vinilos.add(vinilo.toEntity());
        }
        }
  
        UsuarioE.setPedidos(pedidos2);
        UsuarioE.setTarjetas(cards);
        UsuarioE.setFeedBacks(feedbacks);

        UsuarioE.setCarroCompras(vinilos);
        return UsuarioE;
    }

}

