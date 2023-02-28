<script>
  import iziToast from 'izitoast';
  
  iziToast.settings({
    timeout: 10000,
    resetOnHover: true,
    icon: 'material-icons',
    transitionIn: 'flipInX',
    transitionOut: 'flipOutX',
    onOpening: function(){
        console.log('callback abriu!');
    },
    onClosing: function(){
        console.log("callback fechou!");
    }
});

export function ok(title, message){
  iziToast.info({
    title: title,
    message : message,
    color: 'green',
    }
  );

}

</script>
