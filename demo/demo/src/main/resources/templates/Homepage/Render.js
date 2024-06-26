//Import the THREE.js library
import * as THREE from "https://cdn.skypack.dev/three@0.129.0/build/three.module.js";
// To allow for the camera to move around the scene
import { OrbitControls } from "https://cdn.skypack.dev/three@0.129.0/examples/jsm/controls/OrbitControls.js";
// To allow for importing the .gltf file
import { GLTFLoader } from "https://cdn.skypack.dev/three@0.129.0/examples/jsm/loaders/GLTFLoader.js";


//Create a Three.JS Scene
const scene = new THREE.Scene();
//create a new camera with positions and angles
const camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 1000);

//Keep track of the mouse position, so we can make the eye move
let mouseX = window.innerWidth / 2;
let mouseY = window.innerHeight / 2;

//Keep the 3D object on a global variable so we can access it later
let object;

//OrbitControls allow the camera to move around the scene
let controls;

//Set which object to render
let objToRender = 'body.gltf';

//Instantiate a loader for the .gltf file
const loader = new GLTFLoader();

//Load the file
loader.load(
    `/Models/${objToRender}`,
    function (gltf) {
        //If the file is loaded, add it to the scene
        object = gltf.scene;
        object.position.set(0, 0, 0); // Set the position of the object to the center of the scene
        scene.add(object);
    },
    function (xhr) {
        //While it is loading, log the progress
        console.log((xhr.loaded / xhr.total * 100) + '% loaded');
    },
    function (error) {
        //If there is an error, log it
        console.error(error);
    }
);



//Instantiate a new renderer and set its size
const renderer = new THREE.WebGLRenderer({ alpha: true }); //Alpha: true allows for the transparent background
renderer.setSize(window.innerWidth, window.innerHeight);

//Add the renderer to the DOM
document.getElementById("container3D").appendChild(renderer.domElement);

//Set how far the camera will be from the 3D model
camera.position.z = 2.5;

//Add lights to the scene, so we can actually see the 3D model
const topLight = new THREE.DirectionalLight(0xffffff, 1); // (color, intensity)
topLight.position.set(500, 500, 500) //top-left-ish
topLight.castShadow = true;

const bottomLight = new THREE.DirectionalLight(0xffffff, 1); // (color, intensity)
bottomLight.position.set(-500, -500, -500); //bottom-right-ish
bottomLight.castShadow = true;
scene.add(bottomLight);

scene.add(topLight);

const ambientLight = new THREE.AmbientLight(0x333333, 1);
scene.add(ambientLight);

//This adds controls to the camera, so we can rotate / zoom it with the mouse

controls = new OrbitControls(camera, renderer.domElement);
//controls.minDistance = 0.25; // Set the minimum distance for zooming in
//controls.maxDistance = 3; // Set the maximum distnace for zooming out
// Disable zoom and panning
controls.enableZoom = false;
controls.enablePan = false;


//Render the scene
function animate() {
    requestAnimationFrame(animate);
    //Here we could add some code to update the scene, adding some automatic movement

    renderer.render(scene, camera);
}

//Add a listener to the window, so we can resize the window and the camera
window.addEventListener("resize", function () {
    camera.aspect = window.innerWidth / window.innerHeight;
    camera.updateProjectionMatrix();
    renderer.setSize(window.innerWidth, window.innerHeight);
});

const body_parts = document.querySelectorAll('.body-part-nav-item');
console.log(body_parts);

const part_description_offcanvas = new bootstrap.Offcanvas('#part-description')

body_parts.forEach((part) => {
    part.addEventListener('click', (e) => {
        part_description_offcanvas.hide();
        const body_type = e.target.getAttribute('data-body-part');
        console.log(body_type);
        switch (body_type) {
            case 'upper':
                //FIXME: Change the position
                zoomIn(camera, controls, { x: 0, y: 1.25, z: 0.75 }, { x: 0, y: 0, z: 0 });
                break;
            case 'middle':
                zoomIn(camera, controls, { x: 0, y: 0, z: 0.75 }, { x: 0, y: 0, z: 0 });
                break;
            case 'bottom':
                zoomIn(camera, controls, { x: 0, y: -1.25, z: 0.75 }, { x: 0, y: 0, z: 0 });
                break;
            default:
                console.log('No body part selected');
                break;
        }
        part_description_offcanvas.show();
    });
});

// Zoom the camera to the specified position
const zoomIn = (camera, controls, position, rotation) => {
    const targetPosition = new THREE.Vector3(position.x, position.y, position.z);
    const targetRotation = new THREE.Quaternion().setFromEuler(new THREE.Euler(rotation.x, rotation.y, rotation.z));
    const currentPosition = camera.position.clone();
    const currentRotation = new THREE.Quaternion().setFromEuler(camera.rotation.clone());
    const duration = 0.5; // duration in seconds

    console.log(targetRotation, currentRotation);

    let currentTime = 0;

    function updateCamera() {
        currentTime += 0.01; // adjust the speed of the animation here

        const t = Math.min(currentTime / duration, 1); // clamp the value between 0 and 1

        const newPosition = new THREE.Vector3().lerpVectors(currentPosition, targetPosition, t);

        camera.position.copy(newPosition);

        const rotationT = Math.min(currentTime / (duration * 0.5), 1); // adjust the speed of the rotation here
        camera.quaternion.slerp(targetRotation, rotationT); // slerp to the target rotation

        if (t < 1) {
            requestAnimationFrame(updateCamera);
        } else {
            controls.target.copy(newPosition);
            controls.update();
            controls.enableRotate = true; // Enable camera rotation
        }
    }

    updateCamera();
}


//add mouse position listener, so we can make the eye move
document.onmousemove = (e) => {
    mouseX = e.clientX;
    mouseY = e.clientY;
}

//Start the 3D rendering
animate();