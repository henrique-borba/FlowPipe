Vue.component('header-component', {
    template: '<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-primary text-white border-bottom box-shadow">' +
    '    <h5 class="my-0 mr-md-auto font-weight-normal">FlowPipe <b>WMN</b></h5>' +
    '    <nav class="my-2 my-md-0 mr-md-3">' +
    '    </nav>' +
    '    <a class="btn btn-outline-primary" href="#">Shutdown</a>' +
    '</div>',
    data : {

    }
})

Vue.component('flows-component', {
  template : '<div class="pricing-header">\n' +
  '        <h1 class="display-4">Flow</h1>\n' +
  '        <p class="lead">Your current data flow.</p>' +
  '    </div>\n' +
  '    <div class="pricing-header">\n' +
  '        <h2 class="display-10">Status</h2>' +
  '    </div>\n' +
  '    <hr/>\n' +
  '    <div class="card-deck mb-3 text-center">' +
  '        <div class="card mb-4 box-shadow">' +
  '            <div class="card-body">\n' +
  '            </div>\n' +
  '        </div>\n' +
  '        <div class="card mb-4 box-shadow">' +
  '            <div class="card-body">\n' +
  '            </div>\n' +
  '        </div>\n' +
  '        <div class="card mb-4 box-shadow">' +
  '            <div class="card-body">' +
  '            </div>\n' +
  '        </div>\n' +
  '    </div>\n' +
  '    <div class="pricing-header">\n' +
  '        <h2 class="display-10">Flows</h2>' +
  '    </div>\n' +
  '    <hr/>\n' +
  '    <div class="card-deck mb-3 text-center">' +
  '        {{flow_widgets}}\n' +
  '    </div>'
})


Vue.component('cluster-component', {
    template : '<div class="pricing-header">' +
    '        <h1 class="display-4">Cluster</h1>\n' +
    '        <p class="lead">Your current cluster information and configuration.</p>' +
    '    </div>\n' +
    '    <div class="pricing-header">' +
    '        <h2 class="display-10">Status</h2>' +
    '    </div>\n' +
    '    <hr/>\n' +
    '    <div class="card-deck mb-3 text-center">' +
    '        <div class="card mb-4 box-shadow">' +
    '            <div class="card-body">' +
    '            </div>\n' +
    '        </div>\n' +
    '        <div class="card mb-4 box-shadow">' +
    '            <div class="card-body">' +
    '            </div>\n' +
    '        </div>\n' +
    '        <div class="card mb-4 box-shadow">' +
    '            <div class="card-body">' +
    '            </div>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '    <div class="pricing-header">\n' +
    '        <h2 class="display-10">Nodes</h2>' +
    '    </div>\n' +
    '    <hr/>\n' +
    '    <div class="card-deck mb-3 text-center">' +
    '        {{node_widgets}}\n' +
    '    </div>'
})
var app = new Vue({
    el: '#app'
})
