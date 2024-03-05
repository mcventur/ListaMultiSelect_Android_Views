## Ejemplo de RecyclerView multiselección con MaterialCardView

En este caso se usa MaterialCardView como el layout de cada item, porque
cambia de aspecto de forma automática al cambiar su estado de isChecked de true a false. 
Pero podría hacerse con otras vistas, con checkButtons, etc...

El funcionamiento es sencillo: 
 - Se trata de mantener una colección de los elementos marcados en el adaptador. Un Set va bien para este cometido. 
 - En el ViewHolder, asignamos a cada cardView un onClickListener (o cualquier otro evento que nos interese)
para conmutar su estado isChecked, y añadir/quitar el elemento de la colección.
 - Se añade además un LiveData booleano que nos permite que vistas de la UI cambien su estado
si hay o no hay elementos en la colección de seleccionados.
 - Al hacer click en el botón se muestra un log con todos los items marcados, para comprobar que funciona y se pueden procesar dichos items. 
 - Seguidamente, se limpia la selección y se refresca la lista.