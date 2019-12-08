$(document).ready(function(){
    
    customerFormLogic();
    showAccountTo();
    loadCustomers();
    lodgeWithdrawFormLogic();
    transferFormLogic();
    loadAccounts();
    addAccount();
})

function showAccountTo(){
    $('#type_select').change(function(){
        let select_value = $(this).val();
        if(select_value == 'Transfer'){
            $('#transfer_to_div').removeClass('hidden');
        } else {
            $('#transfer_to_div').addClass('hidden');
        }
    })
}

function customerFormLogic(){
    $('#customerForm').submit(function(event){
        event.preventDefault();
        var form_data =  $(this).serializeArray();
        var data  = {};
        for(i=0;i<form_data.length; i++){
            data[form_data[i]['name']] = form_data[i]['value'];
        }
        $.ajax({
            url : 'http://localhost:49000/api/customers',
            method : 'POST',
            contentType : 'application/json',
            data : JSON.stringify(data),
            success: function(res){
                var data = JSON.parse(JSON.stringify(res));
                var account = data['accounts'][0];
                var div = $(`
                <div class="row" id="customer_success">
                  <div class="alert alert-success" role="alert">
                    <strong>Your account was created successfully!</strong>
                    <br /><br />
                    Here is your your account details: <br />
                    Account Number: ${account.accountNo} <br />
                    Credit Card Number: ${account.creditCardNo} <br />
                    Starting Balance: ${account.balance} <br />
                    Sort Code: ${account.sortCode} 
                  </div>
                </div>
                `);
                $('.container').append(div);
            },
            failure: function(err){
                console.log(err);
            }
        })
    })
}

function lodgeWithdrawFormLogic(){
    $('#lodgeWithdrawForm').submit(function(event){
        event.preventDefault();
        let select_value = $('#type_select').val().toLowerCase();
        let form_data =  $(this).serializeArray();
        let data  = {};
        for(i=0;i<form_data.length; i++){
            if(form_data[i]['name'] == 'balance' || form_data[i]['name'] == 'creditCardNo'){
                data[form_data[i]['name']] = Number(form_data[i]['value']);
            } else {
                data[form_data[i]['name']] = form_data[i]['value'];
            }
        }

        //Need to add missing values as null
        data['accountNo'] = 0;
        data['sortCode'] = 0;
        data['transactions'] = [];

        sendTransaction(data, `transactions/${select_value}`)

    });
}

function transferFormLogic(){
    $('#transferForm').submit(function(event){
        event.preventDefault();
        let form_data =  $(this).serializeArray();
        let data  = {};
        let transactions = [];
        for(i=0;i<form_data.length; i++){
            if(form_data[i]['name'] == 'balance' || form_data[i]['name'] == 'creditCardNo'){
                data[form_data[i]['name']] = Number(form_data[i]['value']);
            } else {
                data[form_data[i]['name']] = form_data[i]['value'];
            }
        }
        
        let accountTo = data['accountNoTo'].slice(0);
        delete data['accountNoTo'];
        //Need to add missing values as null
        data['creditCardNo'] = 0;
        data['sortCode'] = 0;
        data['transactions'] = [];
        
        transactions.push({
            accountNo : accountTo,
            balance : data['balance'],
            creditCardNo : 0,
            sortCode : 0,
            transactions : []
        });
        
        transactions.push(data);
        sendTransaction(transactions, `transactions`)

    });
}

function sendTransaction(data, api){
    $.ajax({
        url : `http://localhost:49000/api/${api}`,
        method : 'POST',
        contentType : 'application/json',
        data : JSON.stringify(data),
        success: function(res){
                var transaction = JSON.parse(JSON.stringify(res));
                let div;
                if(transaction instanceof Array){
                    let acc1 = $('#account_select').val();
                    let acc2 = $('#account_select2').val();
                    div = $(`
                        <div class="alert alert-success" role="alert">
                            <strong>Your transfer was successful!</strong>
                            <br /><br />
                            Account 1: ${acc1} <br ?>
                            New Balance: ${transaction[0].postTransBalance} <br ?>
                            ---- <br ?>
                            Account 2: ${acc2} <br ?>
                            New Balance: ${transaction[1].postTransBalance}
                        </div>
                    `)
                } else {
                    div = $(`
                        <div class="alert alert-success" role="alert">
                          <strong>Your ${transaction.description} was successful!</strong>
                          <br /><br />
                          New Account Balance: ${transaction.postTransBalance} <br />
                          Transaction Date: ${transaction.transactionDate} <br />
                          Type: ${transaction.type} 
                        </div>
                    `);
                }

            $('#customer_success').html(div);
        },
        failure: function(err){
            console.log(err);
        }
    })
}

function loadCustomers(){
    $.ajax({ 
        url : 'http://localhost:49000/api/customers',
        method : 'GET',
        contentType : 'application/json',
        success: function(res){
            
            let data = JSON.parse(JSON.stringify(res));
            let options = data.map(x => `<option value="${x.email}">${x.name}</option>`);
            let acc_options = data[0]['accounts'].map(x => `<option>${x.accountNo}</option>`);
            let cc_options = data[0]['accounts'].map(x => `<option>${x.creditCardNo}</option>`);
            
            $('#customer_select').html(options.toString());
            $('#account_select').html(acc_options.toString());
            $('#cc_select').html(cc_options.toString());
            $('.balance-alert').html(`Your current balance is <span id="show_balance">&euro;${data[0].accounts[0].balance}</span>`);
            
        },
        failure: function(err){
            console.log(err);
        }
    })
}

function loadAccounts(){
    $('#customer_select').change(function (){       
        const email = $('#customer_select').val();
        $.ajax({ 
            url : `http://localhost:49000/api/customers/${email}/accounts`,
            method : 'GET',
            contentType : 'application/json',
            success: function(res){
                let data = JSON.parse(JSON.stringify(res));
                let acc_options = data.map(x => `<option>${x.accountNo}</option>`);
                let cc_options = data.map(x => `<option>${x.creditCardNo}</option>`);
                $('#account_select').html(acc_options.toString());
                $('#cc_select').html(cc_options.toString());
                let selected_acc = $('#account_select2').val()
                let acc_options2 = acc_options.filter(x => x !== `<option>${selected_acc}</option>`);
                console.log(acc_options2);
                $('#account_select2').html(acc_options2.toString());
                $('.balance-alert').html(`Your current balance is <span id="show_balance">&euro;${data[0].balance}</span>`);
     
                $('#account_select').change(function(){
                    let new_account_num = $(this);
                    let new_account = data.slice(0).filter(x => x.accountNo);
                    $('.balance-alert').html(`Your current balance is <span id="show_balance">&euro;${new_account.balance}</span>`);
                    let options = $(this).children();
                    if($('#account_select2').length > 0){
                        let acc_options = options.map(x => `<option>${x.val()}</option>`);
                        let selected_acc = $('#account_select2').val()
                        let acc_options2 = acc_options.filter(x => x != `<option>${selected_acc}</option>`);
                        $('#account_select2').html(acc_options2.toString());
                    }
                    
                })
                
            },
            failure: function(err){
                console.log(err);
            }
        })
    });
    
    $('#account_select').change(function(){
        const email = $('#customer_select').val();
        const account_num = $(this).val();
        $('.balance-alert').html(`
            <div class="spinner-border text-info" role="status">
                <span class="sr-only">Loading...</span>
            </div>`);
        $.ajax({ 
            url : `http://localhost:49000/api/customers/${email}/accounts/${account_num}`,
            method : 'GET',
            contentType : 'application/json',
            success: function(res){
                let data = JSON.parse(JSON.stringify(res));
                $('.balance-alert').html(`Your current balance is <span id="show_balance">&euro;${data.balance}</span>`);
            },
            failure:function(err){
                
            }
        })
    })   
}

function addAccount(){
    $('#add_account').click(function(event){
        event.preventDefault();
        const email = $('#customer_select').val();
        $.ajax({ 
            url : `http://localhost:49000/api/customers/${email}`,
            method : 'GET',
            contentType : 'application/json',
            success: function(res){
                const customer = JSON.parse(JSON.stringify(res));
                console.log(res);
                $.ajax({ 
                    url : `http://localhost:49000/api/accounts`,
                    method : 'POST',
                    contentType : 'application/json',
                    data : JSON.stringify(customer),
                    success: function(res2){
                        const account = JSON.parse(JSON.stringify(res2));
                        $('#account_select').append(`<option selected>${account.accountNo}</option>`)
                        var div = `
                            <div class="alert alert-success" role="alert">
                            <strong>New account successfully added!</strong><br /><br />
                            Account Number: ${account.accountNo} <br />
                            Credit Card Number: ${account.creditCardNo} <br />
                            Starting Balance: ${account.balance} <br />
                            </div>
                        `;
                        $('#customer_success').html(div);
                        $('.balance-alert').html(`Your current balance is <span id="show_balance">&euro;100</span>`);
                    },
                    failure: function(err){
                        console.log(err)
                    }
                })
            },
            failure: function(err){
                console.log(err)
                
            }
        })     
    })
}