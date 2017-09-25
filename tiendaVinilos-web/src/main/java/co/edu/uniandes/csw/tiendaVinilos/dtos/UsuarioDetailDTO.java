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
import co.edu.uniandes.csw.tiendaVinilos.entities.TarjetaEntity;
import co.edu.uniandes.csw.tiendaVinilos.entities.UsuarioEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jd.arenas
 */
public class UsuarioDetailDTO extends UsuarioDTO {

    private ArrayList<TarjetaDTO> tarjetas;
    
    private ArrayList<FeedBackDTO> feeds;

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
        ArrayList<TarjetaEntity> lista= entity.getTarjetas();
        List<TarjetaDTO> list= new ArrayList<>();
        for (TarjetaEntity tarjetaEntity : lista) {
            TarjetaDTO dto= new TarjetaDTO(tarjetaEntity);
            list.add(dto);
        }
        tarjetas= (ArrayList<TarjetaDTO>)list;
        ArrayList<FeedBackEntity> lista2= entity.getFeedBacks();
        List<FeedBackDTO> list2= new ArrayList<>();
        for (FeedBackEntity feedback : lista2) {
            FeedBackDTO dto= new FeedBackDTO(feedback);
            list2.add(dto);
        }
        tarjetas= (ArrayList<TarjetaDTO>)list;
        
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
        for (TarjetaDTO tarjeta: tarjetas) {
            cards.add(tarjeta.toEntity());
        }
        ArrayList<FeedBackEntity> feedbacks=new ArrayList<>();
        for (FeedBackDTO feed: feeds) {
            feedbacks.add(feed.toEntity());
        }
  
        UsuarioE.setTarjetas(cards);
        return UsuarioE;
    }

}
