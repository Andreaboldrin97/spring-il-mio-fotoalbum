<template>
  <div class="bg-dark bg-gradient pb-5">
      <HeaderComponent/>
      <main class="container">
           <div class="d-flex mt-3 justify-content-end">
               <div class="d-flex">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" v-model.trim="query" @keyup="getSearchPhoto()">
                    <button class="btn btn-outline-success" type="submit" @click="getSearchPhoto()">Search</button>
               </div>
           </div>
        <div class="row">
            <div class="my-4 col-6" v-for="photo in photos" :key="photo.id" >
                <div class="card m-3 move-card">
                    <div class="card-img d-flex justify-content-center">
                        <img  :src="photo.url" class="card-img-top" alt="image-post"> 
                    </div>
                    <div class="card-body">
                        <h3>{{ photo.title }}</h3>
                        <div>
                            <p class="card-text text-muted" v-if="photo.description == null">La foto non contiene descrizione</p>
                            <p class="card-text" v-else>
                                {{ photo.description }}
                            </p>
                            <h5>#{{ photo.tag }}</h5>
                        </div>
                        <div v-if="photo.categories">
                            <div v-if="photo.categories.length > 0" class="w-100 px-3">
                                <strong>Categorie: </strong>
                                <span class="badge badge-fill bg-success p-2 d-inline-block me-1 text-white" v-for="category in photo.categories" :key="category.id">
                                    {{category.name}} 
                                </span>
                            </div>
                            <div v-else class="mt-1">
                                NON CI SONO CATEGORIE
                            </div>
                        </div>
                        <button v-else @click="getPhotoCategories(photo.id)" class="btn btn-success me-1">categorie</button>

                        <div v-if="photo.comments">
                            <div v-if="photo.comments.length > 0" class="w-100 px-3">
                                <strong>Commenti: </strong>
                                <div class="p-2 me-1" v-for="comment in photo.comments" :key="comment.id">
                                   <span class="badge bg-primary"> {{comment.name}} </span> : <span> {{comment.text}}</span>
                                </div>
                            </div>
                            <div v-else class="mt-1">
                                NON CI SONO COMMENTI
                            </div>
                        </div>
                        <button v-else @click="getPhotoComments(photo.id)" class="btn btn-info me-1">commenti</button>      
                           
                    </div>
                        
                    <div class="card-footer">
                        <button class="btn btn-outline-dark " @click="editCommentPhoto(photo.id)" v-if="photo_id !== photo.id">commenta</button>
                         <div v-else>
                            <div class="w-100">
                                <div class="mb-3">
                                    <input class="form-control" type="text" name="name" v-model.trim="comment_create.name" placeholder="Inserisci il tuo nome">
                                </div>
                                <div class="mb-3">
                                    <div>
                                        <p v-if=" error_comment_text == false"></p>
                                        <p v-else class="text-danger">il commento deve contenere qualcosa</p>
                                    </div>
                                    <input class="form-control" type="text" name="text" v-model.trim="comment_create.text" placeholder="Commenta..">
                                </div>
                                <button class="btn btn-success" @click="createNewComment(photo.id)">invio</button>
                                 <button class="btn ms-2 btn-outline-secondary" @click="photo_id = -1">nascondi</button>
                            </div>
                        </div>
                    </div>                  
                </div>
            </div>
        </div>
      </main>
  </div>
</template>

<script>
//importiamo axios
import axios from 'axios';
import HeaderComponent from './HeaderComponent.vue'
//costante url
const API_URL = "http://localhost:8080/api/1";

export default {
    components: {
       HeaderComponent
    },
  data() {
    return {
        photos : [],
        query : '',
        addBooleanComment : false,
        comment_create : {},
        photo_id : -1,
        error_comment_text : false
    };
  },
  methods: {
    //!PHOTO METHODS
   getAllPhoto(){
        //recuperiamo tutte le foto
        axios.get(API_URL + '/Photo/all')
            .then(res => {
        
                const allPhoto = res.data;
                //se l'array di foto ?? null blocca l'eseguzione
                if (allPhoto == null) return;
                this.photos = allPhoto;
               
            }).catch(error => {
                console.log(error)
            });
   },
    //metodo che recupera' l'index 
    getPhotoIndexById(id) {
        for (let x=0; x<this.photos.length; x++) {
            const photo = this.photos[x];
            if (photo.id == id){
                return x;
            }
        }
        return -1;
    },
     editCommentPhoto(id) {
      this.photo_id = id;
      console.log(this.photo_id )
    },
     //? query api
    getSearchPhoto(){
        console.log(this.query)
        if(this.query == ""){
            this.getAllPhoto();
        }
        axios.get(API_URL + '/Photo/search/'+ this.query )
         .then(res => {
             const allPhoto = res.data;
            //se il risultato ?? null blocca l'eseguzione
            if (allPhoto == null) return;
            this.photos  = allPhoto;   
        }).catch(error => {
            console.log(error)
            });
    },
   //!CATEGORIES METHODS
     getPhotoCategories(photoId) {
      axios.get(API_URL + "/category/by/Photo/" + photoId)
        .then(response => {
            const categories = response.data
            if (categories == null) return
          //recupero l'index dell'elemento
            const index = this.getPhotoIndexById( photoId);
          //recupro l'elemento nell'array by index
            const photo = this.photos[index];
          //aggiungo le categorie
            photo.categories = categories; 
          //sostituisco il vecchio elemento con quello nuovo aggiornato 
            this.photos.splice(index, 1 , photo);
            console.log(this.photos[index].categories);
        })
        .catch(error => {
          console.log(error)
        })
    },
    //!COMMENT METHODS
    //query per ottenere
    getPhotoComments(photoId) {
      axios.get(API_URL + "/comment/by/Photo/" + photoId)
        .then(response => {
            const comments = response.data
            if (comments == null) return
          //recupero l'index dell'elemento
            const index = this.getPhotoIndexById( photoId);
          //recupro l'elemento nell'array by index
            const photo = this.photos[index];
          //aggiungo i commenti
            photo.comments = comments; 
          //sostituisco il vecchio elemento con quello nuovo aggiornato 
            this.photos.splice(index, 1 , photo);
            console.log(this.photos[index]);
        })
        .catch(error => {
          console.log(error)
        })
    },
    //metodo create
    createNewComment(photoId){
        if((this.comment_create.text == null) || (this.comment_create.text == '')){
             this.error_comment_text = true;
             setTimeout(() => this.error_comment_text = false, 2000);
        }
        axios.post(API_URL + "/comment/add/by/Photo/" + photoId, this.comment_create)
        .then(response => {
            const comment = response.data
            if (comment == null) return
          //recupero l'index dell'elemento
            const index = this.getPhotoIndexById( photoId);
          //recupro l'elemento nell'array by index
            const photo = this.photos[index];
          //aggiungo i commenti
            photo.comments.push(comment); 
            //sostituisco il vecchio elemento con quello nuovo aggiornato 
            this.photos.splice(index, 1 , photo);
            //ripotiamo i commenti vuoti
            this.comment_create.name = '';
            this.comment_create.text = '';
        })
        .catch(error => {
          console.log(error)
        })
    },
  },
    mounted() {
        this.getAllPhoto(); 
    },
    created(){
      
    }
  
}
</script>

<style lang='scss' scoped>
    .search{
        width: 350px;
    }
    .card{
        height: 100%;
    }
    .card-img{
        height: 300px;
    }
    img{
        object-fit: cover;
    }
    .move-card{
        object-fit: cover;
        transition: 1s;
        &:hover{
            transform: scale(1.08);
            box-shadow: rgba(0, 0, 0, 0.25) 0px 54px 55px, rgba(0, 0, 0, 0.12) 0px -12px 30px, rgba(0, 0, 0, 0.12) 0px 4px 6px, rgba(0, 0, 0, 0.17) 0px 12px 13px, rgba(0, 0, 0, 0.09) 0px -3px 5px;
        }
    }
</style>