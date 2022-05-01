<script setup>
import dayjs from 'dayjs'


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
        <div class="modal-container bg-[#855B52]">
            <div class="modal-header text-lg font-bold text-white"  >
                <h3> Detail </h3>
            </div>
            <div class="modal-body text-white ">
        <p class="font-medium">START TIME: {{ dayjs(event.eventStartTime).format('DD MMMM YYYY HH.mm')  }}         </p>  
        <p class="font-medium">DURATION:   {{ event.eventDuration }} mins                                          </p>
        <p class="font-medium">CATEGORY:   {{ event.eventCategory.eventCategoryName }}                             </p>
        <p class="font-medium">NAME:       {{ event.bookingName }}                                                 </p>
        <p class="font-medium">EMAIL:      {{ event.bookingEmail }}                                                </p>
        <p class="font-medium">NOTE:       {{ event.eventNotes }}                                                  </p>
            </div>
            <div class=" text-black flex justify-end">
                <button class=" ml-3 mb-4  border border-solid bg-white  border-yellow-800 hover:bg-yellow-800 hover:text-black font-bold uppercase text-sm py-3 rounded outline-none focus:outline-none ease-linear transition-all duration-150 active show px-3"
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