<template>
  <div>
    <!-- progress bar -->
    <Progressbar />

    <div>
      <div>
        <!--all previously submitted info listed here to review-->
        <h1>Review Your Order</h1>

        <!--selected date & time of the event-->
        <div>
          <h3>Selected Date & Time</h3>
          <!--table w specific time information-->
          <table>
            <tr>
              <th>Date</th>
              <td>{{ store.requestData.selectedDate }}</td>
            </tr>
            <!-- You can add time here if you capture it -->
          </table>
        </div>

        <!--customer personal contact info-->
        <div>
          <h2>Personal Contact Information</h2>
          <!--table w specific contact info-->
          <table>
            <tr>
              <th>Name</th>
              <td>{{ store.requestData.fname }} {{ store.requestData.lname }}</td>
            </tr>
            <tr>
              <th>Phone</th>
              <td>{{ store.requestData.pnumber }}</td>
            </tr>
            <tr>
              <th>Email</th>
              <td>{{ store.requestData.email }}</td>
            </tr>
          </table>
        </div>

        <!--inputted event info-->
        <div>
          <h2>Event Information</h2>
          <!--table w specific event information-->
          <table>
            <tr>
              <th>Event Name</th>
              <td>{{ store.requestData.etitle }}</td>
            </tr>
            <tr>
              <th>Organization</th>
              <td>{{ store.requestData.orgname }}</td>
            </tr>
            <tr>
              <th>Event Location</th>
              <td>{{ store.requestData.address }}</td>
            </tr>
            <tr>
              <th>Event Type</th>
              <td>{{ store.requestData.eventType }}</td>
            </tr>
            <tr>
              <th>Any expenses or benefits to the spirit team members</th>
              <td>{{ store.requestData.benefits }}</td>
            </tr>
            <tr>
              <th>Outside organizations</th>
              <td>{{ store.requestData.outorgs }}</td>
            </tr>
            <tr>
              <th>Description of Event</th>
              <td>{{ store.requestData.description }}</td>
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
    <button @click="submitRequest">Submit</button>
  </div>
</template>

<script setup>
import Progressbar from '../components/progressbar.vue';
import { useRouter } from 'vue-router';
import { useStore } from 'pinia';

const router = useRouter();
const store = useStore();

const goToPage3 = () => {
  // go to page 3
  router.push('/page3');
};

const submitRequest = async () => {
  // Update the request status to 'Pending'
  store.updateRequestData({ status: 'Pending' });

  // Send the request data to the backend
  try {
    await store.sendRequestData();
    // Redirect to the confirmation page
    router.push('/page5');
  } catch (error) {
    // Handle any errors
    console.error('Failed to submit request:', error);
    // Optionally, display an error message to the user
  }
};
</script>
