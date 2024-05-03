<template>
  <div>

    <!-- progress bar -->
    <Progressbar />

    <div>
      <div>
        <!--all previously submtted info listed here to review-->
        <h1>Review Your Order</h1>

        <!--selected date & time of the event-->
        <div>
          <h3>Selected Date & Time</h3>
          <!--table w specific time information-->
          <!--TO DO: add time info from input-->
          <table>
            <tr>
              <th>Date</th>
              <td>08/31/2022</td>
            </tr>
            <tr>
              <th>Start Time</th>
              <td>9am</td>
            </tr>
            <tr>
              <th>End Time</th>
              <td>1pm</td>
            </tr>
          </table>
        </div>

        <!--customer personal contact info-->
        <div>
          <h2>Personal Contact Information</h2>
          <!--table w specific contact info-->
          <!--TO DO: add specific contact info from input-->
          <table>
            <tr>
              <th>Name</th>
              <td>John Doe</td>
            </tr>
            <tr>
              <th>Phone</th>
              <td>(817)257-1234</td>
            </tr>
            <tr>
              <th>Email</th>
              <td>johndoe@gmail.com</td>
            </tr>
          </table>
        </div>

        <!--inputted event info-->
        <div>
          <h2>Event Information</h2>
          <!--table w specific event information-->
          <!--TO DO: add specific event info from input-->
          <table>
            <tr>
              <th>Event Name</th>
              <td>John's Graduation Party</td>
            </tr>
            <tr>
              <th>Organization</th>
              <td></td>
            </tr>
            <tr>
              <th>Event Location</th>
              <td></td>
            </tr>
            <tr>
              <th>Event Type</th>
              <td>Private/Residential</td>
            </tr>
            <tr>
              <th>Any expenses or benefits to the spirit team members</th>
                <td></td>
            </tr>
            <tr>
              <th>Outside organizations</th>
              <td></td>
            </tr>
            <tr>
              <th>Description of Event</th>
              <td></td>
            </tr>
          </table>
        </div>
      </div>

      <!--cost info-->
      <div>
        <h2>Total Cost</h2>
        <!--table w specific contact info-->
        <table>
          <tr>
            <!--TO DO: add specific cost info for this event-->
            <td>Super Frog($175.00) X 1.5/hr</td>
          </tr>
          <tr>
            <!--TO DO: calculate total cost-->
            <td>Total Cost: $525</td>
          </tr>
        </table>
      </div>
    </div>

    <!-- Go back to page3 -->
    <button @click="goToPage3">Back</button>

    <!-- Move forward to page 5 -->
    <button @click="goToPage5">Submit</button>
  </div>
</template>

<script setup>
import Progressbar from '../components/progressbar.vue';
import { ref } from 'vue';
import { useRouter } from 'vue-router';
        
const router = useRouter();
const selectedDate = ref('');

const goToPage3 = () => {
  // go to page 3
  router.push('/page3');
};

//TO DO: add any validations to move on
const goToPage5 = () => {
  // go to page 5
  router.push('/page5');
};
</script>

