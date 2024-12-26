﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.Json;
using System.Text.Json.Serialization;
using System.Threading.Tasks;
using System.Xml.XPath;

namespace WinFormsApp1
{
    internal class Courier
    {
        public int Id
        {
            get; set;
        }

        [JsonPropertyName("name")]
        public string Name
        {
            get; set;
        }

        [JsonPropertyName("email")]
        public string Email
        {
            get; set;
        }

        [JsonPropertyName("manager")]
        public Courier? Manager
        {
            get; set;
        }
    }
}
