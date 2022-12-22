<template>
	<header>
		<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">
                    <img class="logo-brand rounded-circle" src="../assets/- INSTAFOTO -.png" alt="">
                    INSTAFOTO
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item mx-3">
                            <a class="nav-link active" aria-current="page" href="#">Home</a>
                        </li>
                        
                    </ul>          
                    <a class="mx-2 btn btn-success" href="http://localhost:8080">ACCEDI</a>
                </div>
            </div>
        </nav>
	</header>
</template>

<script>
export default {

}
</script>

<style scoped>
.logo-brand{
    height: 60px;
}
</style>