package com.courtalon.androidmetierrequester;


import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class GsonRequest<T> extends Request<T> {

    private final Gson gson = new Gson();
    // contiendra le type d'objet a deserialiser
    private final Class<T> clazz;
    // en tete de la requete
    private final Map<String, String> headers;
    private final Response.Listener<T> listener;

    public GsonRequest(String url,
                       Class<T> clazz,
                       Map<String, String> headers,
                       Response.Listener<T> listener,
                       Response.ErrorListener errorListener) {
        // si nécéssaire, on peut maintenant personaliser
        // la méthode de la requete
        super(Method.GET, url, errorListener);
        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;
    }

    // cette fonction sera appelée pour analyser la réponse
    // renvoyée par le serveur
    // c'est ICI que l'on deserialisera le json avec Gson
    // ctr + i -> implement method : pour implementer
    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        // cette methode renvoie la reponse traitée
        // 2 retour possible, soit success, soit error
        try {
            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            // si le json est correctement extrait
            // et convertit en objet
            // on renvoie une Response.success avec l'objet dedans
            return Response.success(
                    gson.fromJson(json, clazz),
                    HttpHeaderParser.parseCacheHeaders(response)
            );
        } catch (UnsupportedEncodingException e) {
           return Response.error(new ParseError(e));
        }
        catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
        // s'il y a un probleme au traitement (erreur de parsing ou de json)
        // on renvoie une Response.error encapsulant l'exception
    }
    // cette methode est responsable de rappeler les listener
    // associés
    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    // fournit les headers par defaut si on en fournit pas
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return (headers != null)? headers : super.getHeaders();
    }
}
