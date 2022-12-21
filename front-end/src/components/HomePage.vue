<template>
  <div>
      <HeaderComponent/>
      <main class="container">
           <div class="d-flex mt-2 justify-content-end">
               <div class="d-flex">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" v-model="query">
                    <button class="btn btn-outline-success" type="submit" @click="getSearchPhoto()">Search</button>
               </div>
           </div>
        <div class="row">
            <div class="my-2 col-6" v-for="photo in photos" :key="photo.id" :class="photo.isVisible ? ' ' : 'd-none'">
                <div class="card m-3">
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
                            <div v-else>
                                NON CI SONO Categorie
                            </div>
                        </div>
                        <button v-else @click="getPhotoCategories(photo.id)" class="btn btn-success me-1">categorie</button>    
                           
                    </div>
                        
                    <div class="card-footer">
                        
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
//costabte url
const API_URL = "http://localhost:8080/api/1";

export default {
    components: {
       HeaderComponent
    },
  data() {
    return {
        photos : [],
        query : '',
    };
  },
  methods: {
    //!PHOTO METHODS
   getAllPhoto(){
        //recuperiamo tutte le foto
        axios.get(API_URL + '/foto/all')
            .then(res => {
        
                const allPhoto = res.data;
                //se l'array di foto è null blocca l'eseguzione
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
     //? query api
    getSearchPhoto(){
        console.log(this.query)
        if(this.query == ""){
            this.getAllPhoto();
        }
        axios.get(API_URL + '/foto/search/'+ this.query )
         .then(res => {
             const allPhoto = res.data;
            //se il risultato è null blocca l'eseguzione
            if (allPhoto == null) return;
            this.photos  = allPhoto;   
        }).catch(error => {
            console.log(error)
            });
    },
   //!CATEGORIES METHODS
     getPhotoCategories(photoId) {
      axios.get(API_URL + "/category/by/foto/" + photoId)
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
    
  },
    mounted() {
        this.getAllPhoto(); 
    },
    created(){
      
    }
  
}
</script>

<style scoped>
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
</style>