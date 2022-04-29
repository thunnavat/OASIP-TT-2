<script setup>
import moment from 'moment'
defineEmits(['close'])
defineProps({
    event: {
       type: Object
        }
})
const date = (date) => {
      return  moment(date).format('DD MMMM YYYY HH.mm');
    }
</script>
 
<template>
<div class="modal-mask">
    <div class="modal-wrapper">
        <div class="modal-container">
            <div class="modal-header text-lg font-bold" >
                <h3>Detail </h3>
            </div>
            <div class="modal-body ">
        <p>START TIME: {{date(event.eventStartTime)}}         </p>  
        <p>DURATION: {{event.eventDuration}} mins             </p>
        <!-- <p>CATEGORY: {{event.eventCategory.eventCategoryName}}</p> -->
        <p>NAME: {{event.bookingName}}                        </p>
        <p>EMAIL:{{event.bookingEmail}}                       </p>
        <p>Note: {{event.eventNotes}}                         </p>
            </div>
            <div class=" text-black flex justify-end">
                <button class="bg-white border-black" @click="$emit('close', false)">Close</button>
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
    background-color: cadetblue;
    border-radius: 2px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
}

.modal-header h3 {
    margin-top: 0;

}

.modal-body {
    margin: 20px 0;
}

</style>