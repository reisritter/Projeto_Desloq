package com.example.jonathan.projetodesloq;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class InicioFragment extends Fragment{

    private Button btnProx;

    public InicioFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       /* btnProx = (Button)getActivity().findViewById(R.id.btnCadPlac);

        btnProx.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           Intent intent = new Intent(getContext(), BuscaDispositivo.class);
                                           startActivity(intent);
                                       }
                                   });*/

        return inflater.inflate(R.layout.fragment_inicio, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        btnProx = (Button) view.findViewById(R.id.btnCadPlac);

        btnProx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BuscaDispositivo.class);
                startActivity(intent);
            }
        });
    }
}
