<script setup>
import moment from 'moment'
defineEmits(['close'])
defineProps({
    event: {
       type: Object
        }
})

</script>
 
<template>
<div class="modal-mask">
    <div class="modal-wrapper">
        <div class="modal-container bg-blue-400">
            <div class="modal-header text-lg font-bold" >
                <h3> Detail </h3>
            </div>
            <div class="modal-body ">
        <p>START TIME: {{ moment(event.eventStartTime).format('DD MMMM YYYY HH.mm')  }}        </p>  
        <p>DURATION:   {{ event.eventDuration }} mins                                          </p>
        <p>CATEGORY:   {{ event.eventCategory.eventCategoryName }}                               </p>
        <p>NAME:       {{ event.bookingName }}                                                 </p>
        <p>EMAIL:      {{ event.bookingEmail }}                                                </p>
        <p>NOTE:       {{ event.eventNotes }}                                                  </p>
            </div>
            <div class=" text-black flex justify-end">
                <button class=" ml-3 mb-4  border border-solid bg-white  border-amber-600 hover:bg-amber-600 hover:text-black active:bg-red-600 font-bold uppercase text-sm py-3 rounded outline-none focus:outline-none ease-linear transition-all duration-150 active show px-3"
                 @click="$emit('close', false)">Close</button>
            </div>
        </div>
    </div>
</div>
</template>

<style scoped>
.modal-mask {
    position: fixed;
    z-index: 9998;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    display: table;
    transition: opacity 0.3s ease;
}

.modal-wrapper {
    display: table-cell;
    vertical-align: middle;
}

.modal-container {
    width: 400px;
    margin: 0px auto;
    padding: 20px 30px;
    border-radius: 2px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
}

.modal-body {
    margin: 20px 0;
}

</style>