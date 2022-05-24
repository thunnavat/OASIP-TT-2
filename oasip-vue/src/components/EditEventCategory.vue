<script setup>
import { ref, computed } from 'vue'

defineEmits(['updateEventCategory'])
const props = defineProps ({
  eventCategory: {
    type : Object ,
    default : {}
  } ,
  eventCategories : {
   type: Array,
   default: []
  }
})
const categoryErrorMsg = ref('')
const showCategoryError = ref(false)
const descErrorMsg = ref('')
const showDescError = ref(false)

const newEventCategory = computed(() => {
  return {
    id: props.eventCategory.id,
    eventCategoryName: props.eventCategory.eventCategoryName ,
    eventCategoryDescription: props.eventCategory.eventCategoryDescription ,
    eventDuration: props.eventCategory.eventDuration 
  }
})

const isDisabled = ref(false)

const disable = () => {
 if(newEventCategory.value.eventDuration > 480 || newEventCategory.value.eventDuration < 1 ){
 isDisabled.value = true
 }else
 isDisabled.value = false
}


const repeatablename = ref(false)
const checkName = () => {
  const categoryByName = props.eventCategories.find(e => e.eventCategoryName.trim().toLowerCase() === newEventCategory.value.eventCategoryName.trim().toLowerCase())
  if(categoryByName !== undefined && categoryByName.id !== newEventCategory.value.id) {
    isDisabled.value = true
    showCategoryError.value =true
    categoryErrorMsg.value = 'Category Name must be unique Unique'
  } 
  else if (newEventCategory.value.eventCategoryName.length > 100 || newEventCategory.value.eventCategoryName.length < 1){
    isDisabled.value = true
    showCategoryError.value = true
    categoryErrorMsg.value = 'Category Name must be between 1 to 100 charaters'
  }
  else {
    isDisabled.value = false
    showCategoryError.value = false
  }
}

const checkDesc = () => {
  if(newEventCategory.value.eventCategoryDescription.length > 500){
    isDisabled.value = true
    showDescError.value = true
    descErrorMsg.value = 'Description must not exceed 500 characters'
  }else{
    isDisabled.value = false
    showDescError.value = false
  }
}
</script>
 
<template>
<div>  
  <span v-show="showCategoryError" class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded absolute mx-20 top-44 ">{{ categoryErrorMsg }} <button @click="showCategoryError = false">x</button></span>
  <p class="text-3xl font-bold mb-3 ml-4 mt-6	">CATEGORY NAME</p>
  <p class="ml-4 "> <span class="font-bold"> CATEGORY NAME : </span>  <input type="text"  maxlength="101" class="border-2 border-black text-black ml-1 mt-16 bg-zinc-300 disabled:opacity-50 disabled:hover:cursor-not-allowed	" v-model="newEventCategory.eventCategoryName" :onchange="checkName">
  <span class="font-bold"> DURATION : </span> <input type="number"  min="1" max="480"   class="border-2 border-black text-black ml-1 mt-2 bg-zinc-300 disabled:opacity-50 disabled:hover:cursor-not-allowed" v-model="newEventCategory.eventDuration" :onchange="disable">
  </p>
  <span v-show="showDescError" class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative mx-20 top-4 left-14">{{ descErrorMsg }} <button @click="showDescError = false">x</button></span>
  <p><span class="font-bold ml-4"> DESCRIPTION : </span> <br>
  <textarea cols="50" rows="3" maxlength="501" class="border-2 border-black text-black ml-4 mt-1 bg-zinc-300 disabled:opacity-50 disabled:hover:cursor-not-allowed	" v-model="newEventCategory.eventCategoryDescription" :onchange="checkDesc"></textarea> </p>
  <button :disabled="isDisabled"  @click="isDisabled === true ? '' : $emit('updateEventCategory', {id: newEventCategory.id, eventCategoryName: newEventCategory.eventCategoryName.trimStart().trimEnd() , eventCategoryDescription: newEventCategory.eventCategoryDescription, eventDuration: newEventCategory.eventDuration})" 
   class="text-white bg-black mr-4 border border-solid hover:bg-[#855B52]  active:bg-cyan-600 font-bold uppercase text-sm py-3 rounded outline-none focus:outline-none ease-linear transition-all duration-150 active show px-3 disabled:opacity-50 disabled:hover:cursor-not-allowed ml-4 mb-4"> Save</button>
</div>
</template>
 
<style>

</style>