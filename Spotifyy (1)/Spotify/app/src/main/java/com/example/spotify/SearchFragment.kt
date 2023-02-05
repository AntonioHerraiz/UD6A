package com.example.spotify

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spotify.databinding.FragmentSearchBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var recyclerView : RecyclerView
    private lateinit var binding: FragmentSearchBinding
    var playlists = mutableListOf<PlaylistData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.playlistRecyclerView
        recyclerView.adapter = PopularListAdapter(playlists)
        var gridLayout : GridLayoutManager  = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = gridLayout

        val playlists = mutableListOf<PlaylistData>(
            PlaylistData("Éxitos España", "2.135.132 seguidores", "https://i1.sndcdn.com/artworks-000741044260-zqtcap-t500x500.jpg", mutableListOf<Song>(Song("Quédate", "Quevedo", "https://i.scdn.co/image/ab67706f00000003a76a2f381705db3d9deff51d", false), Song("Salpique", "Shakira", "https://i.scdn.co/image/ab67706f00000002484ca85214755c563d9e2cd4", false ), Song("La Jumpa", "Arcángel", "https://m.media-amazon.com/images/I/41+WUp7mz4L._UXNaN_FMjpg_QL85_.jpg", false), Song("Sin Señal", "Quevedo", "https://i.scdn.co/image/ab67616d0000b2739b7802966093bdc511142515", false), Song("APA", "Mora, Quevedo", "https://i.ytimg.com/vi/NbLPcrGxioI/hqdefault.jpg", false), Song("Cairo", "Karol G", "https://jenesaispop.com/wp-content/uploads/2022/11/karol-g-cairo.jpg", false),Song("Puta España music", "José Bretón", "https://i.scdn.co/image/ab6761610000e5eb1e872f6041e22b66ac98aafa", false), Song("Flowers", "Miley Cirus",
                "https://i.scdn.co/image/ab67616d0000b273f429549123dbe8552764ba1d", false),Song("La bachata", "Manuel Turizo", "https://i1.sndcdn.com/artworks-pEWZ3m9PGcB0fXwV-c7y1mg-t500x500.jpg", false), Song("Hope", "Kidd Keo", "https://m.media-amazon.com/images/I/51943d3LOkL._UXNaN_FMjpg_QL85_.jpg", false), Song("No me renta iii", "Ezvir 810", "https://i.scdn.co/image/ab67616d00001e0295afce08b538a4740f14066d", false))),
            PlaylistData("Pop con Ñ", "758.825 seguidores", "https://i.scdn.co/image/ab67616d0000b27377d8d4cca317e6e96ff2509b",mutableListOf<Song>(Song("Me matas", "Noah", "https://cdn.smehost.net/sonymusices-45pressprod/wp-content/uploads/2022/11/e11557c0-7c2f-48fe-83d2-138a7d850ca4.jpg", false), Song("Que bonita", "Depol", "https://i.scdn.co/image/ab67616d0000b2739c08563a9b03828119c3109b", false ), Song("Santorini", "Enol", "https://images.genius.com/ad398d2178e60375ab732124817f48c2.640x640x1.jpg", false), Song("Como yo", "Pole", "https://i.scdn.co/image/ab67616d0000b273625ee98e4218037c8a970b8c", false), Song("Diablo", "Beret", "https://okdiario.com/img/2022/08/30/beret-y-estopa..jpg", false), Song("506", "Morat", "https://i.ytimg.com/vi/jXiWUTzJ2W8/maxresdefault.jpg", false))),
            PlaylistData("Pop Clásico", "234.422 seguidores", "https://img.freepik.com/vector-premium/letras-vector-3d-vintage-pop-clasico-fuente-retro-negrita-tipografia-texto-estilizado-arte-pop-letras-estilo-vieja-escuela-cartel-anos-90-80-banner-diseno-tipografia-camiseta-fondo-color-turquesa_106317-8397.jpg?w=2000", mutableListOf<Song>(Song("Tú y yo", "Beret", "https://i.scdn.co/image/ab67616d0000b273cb8dffe85b3fa5886f1c06ce", false), Song("Radio 2000", "David Rees", "https://m.media-amazon.com/images/I/31-YkPqueaL._UXNaN_FMjpg_QL85_.jpg", false ), Song("Valeria", "Dvicio", "https://i.ytimg.com/vi/14F2ed2AWdM/maxresdefault.jpg ", false), Song("Roto y elegante", "Taburete", "https://thisis-images.scdn.co/37i9dQZF1DZ06evO2lb2Cc-default.jpg", false), Song("Presiento", "Morat", "https://i.scdn.co/image/ab67616d0000b27351358468b00831d9eb74ac51", false), Song("Despertar", "Estopa", "https://www.diariodealmeria.es/2022/05/31/ocio/Estopa-actuara-Feria-Almeria_1688542584_159548963_667x375.jpg", false),Song("En el coche", "Aitana", "https://i.ytimg.com/an/l66Ub-GZ578/11239646263888610072_mq.jpg?v=6246bf5a", false), Song("Flowers", "Miley Cirus",
                "https://i.scdn.co/image/ab67616d0000b273f429549123dbe8552764ba1d", false))),
            PlaylistData("Pop Internacional", "26.195.307 seguidores", "https://akamai.sscdn.co/tb/1950x1950/palcomp3-playlists/b0026dba-3288-483e-9f8a-7480cb058ca2.jpg", mutableListOf<Song>(Song("Hello", "Adele", "https://pics.filmaffinity.com/Adele_Hello_V_deo_musical-938814499-mmed.jpg", false), Song("Stay", "Zeed", "https://upload.wikimedia.org/wikipedia/en/9/9f/Stay_Zedd_and_Alessia_Cara.jpg", false ), Song("Beautiful", "Vigo", "https://is2-ssl.mzstatic.com/image/thumb/Music49/v4/68/d7/bb/68d7bb7f-6cc0-cc49-2cfa-75620c693b29/artwork.jpg/400x400bb.jpg ", false), Song("Favela", "Alok", "https://i.ytimg.com/vi/P0fN4CtsttM/maxresdefault.jpg", false), Song("Halo", "Beyonce", "https://i.discogs.com/nf5SuGAUPxmX_csf7eumdU0hOO30M9AJ2maoau7AP0A/rs:fit/g:sm/q:90/h:600/w:600/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTE4Nzk3/MDItMTU3MDAxNjM2/My04MTY1LmpwZWc.jpeg", false), Song("Maps", "Marron 5", "https://upload.wikimedia.org/wikipedia/en/3/3e/Maroon_5_Maps_cover.png ", false))),
            PlaylistData("Rap Español", "234.422 seguidores", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQJahGE0oOOMnyegkPevxszoXHVuHW8nSIsUg&", mutableListOf<Song>(Song("Quédate", "Quevedo", "https://i.scdn.co/image/ab67706f00000003a76a2f381705db3d9deff51d", false), Song("Salpique", "Shakira", "https://i.scdn.co/image/ab67706f00000002484ca85214755c563d9e2cd4", false ), Song("La Jumpa", "Arcángel", "https://m.media-amazon.com/images/I/41+WUp7mz4L._UXNaN_FMjpg_QL85_.jpg", false), Song("Sin Señal", "Quevedo", "https://i.scdn.co/image/ab67616d0000b2739b7802966093bdc511142515", false), Song("APA", "Mora, Quevedo", "https://i.ytimg.com/vi/NbLPcrGxioI/hqdefault.jpg", false), Song("Cairo", "Karol G", "https://jenesaispop.com/wp-content/uploads/2022/11/karol-g-cairo.jpg", false),Song("Puta España music", "José Bretón", "https://i.scdn.co/image/ab6761610000e5eb1e872f6041e22b66ac98aafa", false), Song("Flowers", "Miley Cirus",
                "https://i.scdn.co/image/ab67616d0000b273f429549123dbe8552764ba1d", false))),
            PlaylistData("Mix Pop", "26.195.307 seguidores", "https://i1.sndcdn.com/artworks-000171547298-mgze4w-t500x500.jpg", mutableListOf<Song>(Song("Quédate", "Quevedo", "https://i.scdn.co/image/ab67706f00000003a76a2f381705db3d9deff51d", false), Song("Salpique", "Shakira", "https://i.scdn.co/image/ab67706f00000002484ca85214755c563d9e2cd4", false ), Song("La Jumpa", "Arcángel", "https://m.media-amazon.com/images/I/41+WUp7mz4L._UXNaN_FMjpg_QL85_.jpg", false), Song("Sin Señal", "Quevedo", "https://i.scdn.co/image/ab67616d0000b2739b7802966093bdc511142515", false), Song("APA", "Mora, Quevedo", "https://i.ytimg.com/vi/NbLPcrGxioI/hqdefault.jpg", false), Song("Cairo", "Karol G", "https://jenesaispop.com/wp-content/uploads/2022/11/karol-g-cairo.jpg", false),Song("Puta España music", "José Bretón", "https://i.scdn.co/image/ab6761610000e5eb1e872f6041e22b66ac98aafa", false), Song("Flowers", "Miley Cirus",
                "https://i.scdn.co/image/ab67616d0000b273f429549123dbe8552764ba1d", false)))
        )

            val action = { position: Int ->
                val intent = Intent(requireContext(), PlaylistSongsActivity::class.java)
                intent.putExtra("playlist", playlists[position])
                startActivity(intent)
            }
            val myAdatper = recyclerView.adapter as PopularListAdapter
            myAdatper.setAction(playlists, action)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}